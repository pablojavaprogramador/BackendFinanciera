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
import com.mibolsillo.model.Compra;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.service.BitcoinService;
import com.mibolsillo.service.CompraService;
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
public class CompraController {

	
	@Autowired
	private CompraService compraService;

	
	@RequestMapping(value = "/compra/{id}", method = RequestMethod.GET)
	ResponseEntity<Compra> consultarCupones(@PathVariable Long id) {
		Compra bitcoin=compraService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = compraService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<Compra>(bitcoin,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/compra", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarCupones(@RequestBody Compra bitcoin) {
		RespuestaOk respuestaOk = compraService.save(bitcoin);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/compra", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarCupones(@RequestBody Compra bitcoin) {
		
		if (bitcoin.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
//        if (compraService.existsById(bitcoin.getId())==false) {
//        	throw new BadRequestAlertException("No existe el id a actualizar");
//        }
        
        
		RespuestaOk actualizarDomicilio= compraService.actualizar(bitcoin);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/compra", method = RequestMethod.DELETE)
	Map<String, String> eliminarCupones(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		Compra bitcoin = compraService.findById(id);
	    	boolean referenciaid = compraService.existsById(id);
	        if(referenciaid==true) {
	        	compraService.delete(bitcoin);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/compra", method = RequestMethod.GET)
	public ResponseEntity<List<Compra>> consultaCupones() {
		List<Compra> bitcoin = compraService.findAll();
		return new ResponseEntity<List<Compra>>(bitcoin,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/compraall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarCupones(@RequestBody List<Compra> listabitcoin) {
		RespuestaOk bitcoin=compraService.saveAll(listabitcoin);
		return new ResponseEntity<RespuestaOk>(bitcoin,HttpStatus.OK);
	}

	@RequestMapping(value = "/compra/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllCupones() {
		compraService.deleteAll();
		return "SUCCESS";
	}

}
