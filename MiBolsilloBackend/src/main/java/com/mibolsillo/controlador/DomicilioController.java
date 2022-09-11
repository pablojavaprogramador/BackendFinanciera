package com.mibolsillo.controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import com.bolsillo.model.error.ErrorMessage;
import com.bolsillo.model.error.ModeloNotFoundException;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.service.DomicilioService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class DomicilioController {

	
	@Autowired
	private DomicilioService domicilioService;

	
	@GetMapping
	public String info () {
		return("Hola");
		
	}
	
	
	 @RequestMapping(value = "/domicilios/{id}", method = RequestMethod.GET)
	//@GetMapping("/domicilios/{id}")
	ResponseEntity<Domicilio> consultarDomicilio(@PathVariable Long id) throws ModeloNotFoundException {
	
	
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		Domicilio domicilio = domicilioService.findById(id);

		if (domicilio.getId() == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<Domicilio>(domicilio, HttpStatus.OK);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/domicilios", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarDomicilio(@RequestBody  Domicilio domicilio) {
		RespuestaOk respuestadomicilio = domicilioService.save(domicilio);
	
		return new ResponseEntity<RespuestaOk>(respuestadomicilio,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/domicilios", method = RequestMethod.PUT)
	public  ResponseEntity<RespuestaOk> actualizarDomicilio(@RequestBody Domicilio domicilio) {
		
		//  domicilio = domicilioService.findById(domicilio.getId());

		  if (domicilio.getId() == null) {
	            throw new BadRequestAlertException("Id Invalido");
	        }
	  
	        if (domicilioService.existsById(domicilio.getId())==false) {
	        	throw new BadRequestAlertException("No existe el id a actualizar");
	        }
		    
		     RespuestaOk actualizarDomicilio= domicilioService.update(domicilio);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK) ;
	}

//	@RequestMapping(value = "/domicilios", method = RequestMethod.DELETE)
//	Map<String, String> eliminarDomicilio(@RequestParam Long id) {
//		Map<String, String> status = new HashMap<>();
//	        Optional<Domicilio> articulo = domicilioService.findById(id);
//	        if(articulo.isPresent()) {
//	        	domicilioService.delete(articulo.get());
//	            status.put("Estatus", "Se elimino el domicilio correctamente");
//	        }
//	        else {
//	            status.put("Estatus", "El Domicilio no se ha encontrado");
//	        } 
//		return status;
//	}




	@RequestMapping(value = "/domicilios", method = RequestMethod.GET)
	public ResponseEntity<List<Domicilio>> consultaDomicilios() {
		List<Domicilio> response =domicilioService.findAll();
	return new ResponseEntity<List<Domicilio>> (response,HttpStatus.OK) ;
	}

	@RequestMapping(value = "/todoslosdomicilios", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarDomicilio(@RequestBody List<Domicilio> listaDomicilio) {
		RespuestaOk domicilios = domicilioService.saveAll(listaDomicilio);
		return new ResponseEntity<RespuestaOk>(domicilios,HttpStatus.OK);
	}

	@RequestMapping(value = "/domicilios", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarAllDomicilios() {
		domicilioService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
