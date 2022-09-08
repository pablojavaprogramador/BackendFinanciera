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
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.service.BitcoinService;
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
public class BitcoinsController {

	
	@Autowired
	private BitcoinService bitcoinService;

	
	@RequestMapping(value = "/bitcoins/{id}", method = RequestMethod.GET)
	ResponseEntity<Bitcoin> consultarCupones(@PathVariable Long id) {
		Bitcoin bitcoin=bitcoinService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = bitcoinService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<Bitcoin>(bitcoin,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/bitcoins", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarCupones(@RequestBody Bitcoin bitcoin) {
		RespuestaOk respuestaOk = bitcoinService.save(bitcoin);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/bitcoins", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarCupones(@RequestBody Bitcoin bitcoin) {
		
		if (bitcoin.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (bitcoinService.existsById(bitcoin.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
        
		RespuestaOk actualizarDomicilio= bitcoinService.actualizar(bitcoin);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/bitcoins", method = RequestMethod.DELETE)
	Map<String, String> eliminarCupones(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		Bitcoin bitcoin = bitcoinService.findById(id);
	    	boolean referenciaid = bitcoinService.existsById(id);
	        if(referenciaid==true) {
	        	bitcoinService.delete(bitcoin);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/bitcoins", method = RequestMethod.GET)
	public ResponseEntity<List<Bitcoin>> consultaCupones() {
		List<Bitcoin> bitcoin = bitcoinService.findAll();
		return new ResponseEntity<List<Bitcoin>>(bitcoin,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/bitcoinsall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarCupones(@RequestBody List<Bitcoin> listabitcoin) {
		RespuestaOk bitcoin=bitcoinService.saveAll(listabitcoin);
		return new ResponseEntity<RespuestaOk>(bitcoin,HttpStatus.OK);
	}

	@RequestMapping(value = "/bitcoins/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllCupones() {
		bitcoinService.deleteAll();
		return "SUCCESS";
	}

}
