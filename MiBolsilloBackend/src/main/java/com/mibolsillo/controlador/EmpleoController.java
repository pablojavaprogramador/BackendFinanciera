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
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.service.EmpleoService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class EmpleoController {

	
	@Autowired
	private EmpleoService empleoService;

	
	@RequestMapping(value = "/empleos/{id}", method = RequestMethod.GET)
	ResponseEntity<Empleos> consultarempleo(@PathVariable Long id) {
		Empleos respuesta = empleoService.findById(id);
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean domicilio = empleoService.existsById(id);

		if (domicilio==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		return new ResponseEntity<Empleos>(respuesta,HttpStatus.OK);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/empleos", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarempleo(@RequestBody Empleos empleo) {
		RespuestaOk crearempleo= empleoService.save(empleo);
		return new ResponseEntity<RespuestaOk>(crearempleo,HttpStatus.OK);

	}

	@RequestMapping(value = "/empleos", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarempleo(@RequestBody Empleos empleo) {
		
		  if (empleo.getId() == null) {
	            throw new BadRequestAlertException("Id Invalido");
	        }
	  
	        if (empleoService.existsById(empleo.getId())==false) {
	        	throw new BadRequestAlertException("No existe el id a actualizar");
	        }
	        
		RespuestaOk actualizarempleo= empleoService.actualizar(empleo);
		
		return new ResponseEntity<RespuestaOk>(actualizarempleo,HttpStatus.OK);
	}

	@RequestMapping(value = "/empleos/eliminar", method = RequestMethod.DELETE)
	Map<String, String> eliminarempleo(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
	       Empleos empleo = empleoService.findById(id);
	       boolean existe = empleoService.existsById(id);
	        if(existe==true) {
	        	empleoService.delete(empleo);
	            status.put("Estatus", "Se elimino el empleo correctamente");
	        }
	        else {
	            status.put("Estatus", "El empleo no se ha encontrado");
	        } 
		return status;
	}



	@RequestMapping(value = "/empleos", method = RequestMethod.GET)
	public List<Empleos> consultaempleos() {
		return empleoService.findAll();
	}

	@RequestMapping(value = "/empleoslista", method = RequestMethod.POST)
	public String agregarempleo(@RequestBody List<Empleos> listaempleo) {
		empleoService.saveAll(listaempleo);
		return "SUCCESS";
	}

	@RequestMapping(value = "/empleos", method = RequestMethod.DELETE)
	public String eliminarAllempleos() {
		empleoService.deleteAll();
		return "SUCCESS";
	}

}
