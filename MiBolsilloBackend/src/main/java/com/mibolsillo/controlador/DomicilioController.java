package com.mibolsillo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mibolsillo.model.Domicilio;
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
	Optional<Domicilio> consultarDomicilio(@PathVariable Long id) {
		return domicilioService.findById(id);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/domicilios", method = RequestMethod.POST)
	public String agregarDomicilio(@RequestBody Domicilio domicilio) {
		Domicilio salvarDomicilio = domicilioService.save(domicilio);

		return "{\"mensaje\":\"Articulo Guardado Correctamente\"}";
	}

	@RequestMapping(value = "/domicilios", method = RequestMethod.PUT)
	public String actualizarDomicilio(@RequestBody Domicilio domicilio) {
		Domicilio actualizarDomicilio= domicilioService.save(domicilio);
		 	return "{\"mensaje\":\"Domicilio Actualizado Correctamente\"}";
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

	private Long Long(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@RequestMapping(value = "/domicilios", method = RequestMethod.GET)
	public List<Domicilio> consultaDomicilios() {
		return domicilioService.findAll();
	}

	@RequestMapping(value = "/domiciliosmuchos", method = RequestMethod.POST)
	public String agregarDomicilio(@RequestBody List<Domicilio> listaDomicilio) {
		domicilioService.saveAll(listaDomicilio);
		return "SUCCESS";
	}

	@RequestMapping(value = "/domicilios", method = RequestMethod.DELETE)
	public String eliminarAllDomicilios() {
		domicilioService.deleteAll();
		return "SUCCESS";
	}

}
