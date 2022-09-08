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
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.service.CuponesService;
import com.mibolsillo.service.DomicilioService;
import com.mibolsillo.service.ReferenciasPersonales;
import com.mibolsillo.service.SolicitudCreditoService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class CuponesController {

	
	@Autowired
	private CuponesService cuponesService;

	
	@RequestMapping(value = "/cupones/{id}", method = RequestMethod.GET)
	ResponseEntity<Cupones> consultarCupones(@PathVariable Long id) {
		Cupones cupones=cuponesService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = cuponesService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<Cupones>(cupones,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/cupones", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarCupones(@RequestBody Cupones cupones) {
		RespuestaOk respuestaOk = cuponesService.save(cupones);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/cupones", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarCupones(@RequestBody Cupones cupones) {
		
		if (cupones.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (cuponesService.existsById(cupones.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
        
		RespuestaOk actualizarDomicilio= cuponesService.actualizar(cupones);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/cupones", method = RequestMethod.DELETE)
	Map<String, String> eliminarCupones(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		Cupones cupones = cuponesService.findById(id);
	    	boolean referenciaid = cuponesService.existsById(id);
	        if(referenciaid==true) {
	        	cuponesService.delete(cupones);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/cupones", method = RequestMethod.GET)
	public ResponseEntity<List<Cupones>> consultaCupones() {
		List<Cupones> cupones = cuponesService.findAll();
		return new ResponseEntity<List<Cupones>>(cupones,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/cuponesall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarCupones(@RequestBody List<Cupones> listasolicitudCredito) {
		RespuestaOk cupones=cuponesService.saveAll(listasolicitudCredito);
		return new ResponseEntity<RespuestaOk>(cupones,HttpStatus.OK);
	}

	@RequestMapping(value = "/cupones/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllCupones() {
		cuponesService.deleteAll();
		return "SUCCESS";
	}

}
