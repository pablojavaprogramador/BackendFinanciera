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
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.service.DomicilioService;
import com.mibolsillo.service.ReferenciasPersonales;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class ReferenciasPersonalesController {

	
	@Autowired
	private ReferenciasPersonales referenciasService;

	
	@RequestMapping(value = "/referencia-personales/{id}", method = RequestMethod.GET)
	ResponseEntity<ReferenciaPersonales> consultarReferencia(@PathVariable Long id) {
		ReferenciaPersonales referenciaPersonales=referenciasService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = referenciasService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<ReferenciaPersonales>(referenciaPersonales,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/referencia-personales", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarReferencia(@RequestBody ReferenciaPersonales referencia) {
		RespuestaOk respuestaOk = referenciasService.save(referencia);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/referencia-personales", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarDomicilio(@RequestBody ReferenciaPersonales referencia) {
		
		if (referencia.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (referenciasService.existsById(referencia.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
        
		RespuestaOk actualizarDomicilio= referenciasService.actualizar(referencia);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/referencia-personales", method = RequestMethod.DELETE)
	Map<String, String> eliminarReferenciaPersonal(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
	        ReferenciaPersonales referencia = referenciasService.findById(id);
	    	boolean referenciaid = referenciasService.existsById(id);
	        if(referenciaid==true) {
	        	referenciasService.delete(referencia);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/referencia-personales", method = RequestMethod.GET)
	public ResponseEntity<List<ReferenciaPersonales>> consultaReferencia() {
		List<ReferenciaPersonales> referencias = referenciasService.findAll();
		return new ResponseEntity<List<ReferenciaPersonales>>(referencias,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/referencia-personales", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarReferencias(@RequestBody List<ReferenciaPersonales> listaReferencia) {
		RespuestaOk referencias=referenciasService.saveAll(listaReferencia);
		return new ResponseEntity<RespuestaOk>(referencias,HttpStatus.OK);
	}

	@RequestMapping(value = "/referencia-personales/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllDomicilios() {
		referenciasService.deleteAll();
		return "SUCCESS";
	}

}
