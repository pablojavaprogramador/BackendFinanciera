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

import com.mibolsillo.model.Empleos;
import com.mibolsillo.service.EmpleoService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class EmpleoController {

	
	@Autowired
	private EmpleoService empleoService;

	

	@GetMapping("infoempleo")
	public String info () {
		return("Hola");
		
	}
	
	
	@RequestMapping(value = "/empleo/{id}", method = RequestMethod.GET)
	Optional<Empleos> consultarempleo(@PathVariable Long id) {
		return empleoService.findById(id);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/empleo", method = RequestMethod.POST)
	public String agregarempleo(@RequestBody Empleos empleo) {
		Empleos salvarempleo = empleoService.save(empleo);

		return "{\"mensaje\":\"Articulo Guardado Correctamente\"}";
	}

	@RequestMapping(value = "/empleo", method = RequestMethod.PUT)
	public String actualizarempleo(@RequestBody Empleos empleo) {
		Empleos actualizarempleo= empleoService.save(empleo);
		 	return "{\"mensaje\":\"empleo Actualizado Correctamente\"}";
	}

//	@RequestMapping(value = "/empleo", method = RequestMethod.DELETE)
//	Map<String, String> eliminarempleo(@RequestParam Long id) {
//		Map<String, String> status = new HashMap<>();
//	        Optional<Empleos> articulo = empleoService.findById(id);
//	        if(articulo.isPresent()) {
//	        	empleoService.delete(articulo.get());
//	            status.put("Estatus", "Se elimino el empleo correctamente");
//	        }
//	        else {
//	            status.put("Estatus", "El empleo no se ha encontrado");
//	        } 
//		return status;
//	}



	@RequestMapping(value = "/empleo", method = RequestMethod.GET)
	public List<Empleos> consultaempleos() {
		return empleoService.findAll();
	}

	@RequestMapping(value = "/empleo", method = RequestMethod.POST)
	public String agregarempleo(@RequestBody List<Empleos> listaempleo) {
		empleoService.saveAll(listaempleo);
		return "SUCCESS";
	}

	@RequestMapping(value = "/empleo", method = RequestMethod.DELETE)
	public String eliminarAllempleos() {
		empleoService.deleteAll();
		return "SUCCESS";
	}

}
