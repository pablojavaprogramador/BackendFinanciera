package com.mibolsillo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bolsillo.model.error.BadRequestAlertException;
import com.bolsillo.model.error.ModeloNotFoundException;
import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Cliente;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.service.BitcoinService;
import com.mibolsillo.service.ClienteService;
import com.mibolsillo.service.CuponesService;
import com.mibolsillo.service.DomicilioService;
import com.mibolsillo.service.PagosService;
import com.mibolsillo.service.ReferenciasPersonales;
import com.mibolsillo.service.SolicitudCreditoService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class ClienteController {

	
	@Autowired
	private ClienteService clienteService;

	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
	ResponseEntity<Cliente> consultarCupones(@PathVariable Long id) {
		Cliente cliente=clienteService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = clienteService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/clientes", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarCupones(@RequestBody Cliente cliente) {
		RespuestaOk respuestaOk = clienteService.save(cliente);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/clientes", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarCupones(@RequestBody Cliente cliente) {
		
		if (cliente.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (clienteService.existsById(cliente.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
        
		RespuestaOk actualizarDomicilio= clienteService.actualizar(cliente);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/clientes", method = RequestMethod.DELETE)
	Map<String, String> eliminarCupones(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		Cliente cliente = clienteService.findById(id);
	    	boolean referenciaid = clienteService.existsById(id);
	        if(referenciaid==true) {
	        	clienteService.delete(cliente);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> consultaCupones() {
		List<Cliente> cliente = clienteService.findAll();
		return new ResponseEntity<List<Cliente>>(cliente,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/clientesall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarCupones(@RequestBody List<Cliente> listacliente) {
		RespuestaOk cliente=clienteService.saveAll(listacliente);
		return new ResponseEntity<RespuestaOk>(cliente,HttpStatus.OK);
	}

	@RequestMapping(value = "/clientes/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllCupones() {
		clienteService.deleteAll();
		return "SUCCESS";
	}

}
