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
import com.mibolsillo.model.ReferenciaPersonales;
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
	Optional<ReferenciaPersonales> consultarReferencia(@PathVariable Long id) {
		return referenciasService.findById(id);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/referencia-personales", method = RequestMethod.POST)
	public String agregarReferencia(@RequestBody ReferenciaPersonales referencia) {
		ReferenciaPersonales salvarDomicilio = referenciasService.save(referencia);

		return "{\"mensaje\":\"Referencia Guardada Correctamente\"}";
	}

	@RequestMapping(value = "/referencia-personales", method = RequestMethod.PUT)
	public String actualizarDomicilio(@RequestBody ReferenciaPersonales referencia) {
		ReferenciaPersonales actualizarDomicilio= referenciasService.save(referencia);
		 	return "{\"mensaje\":\"Referencia  Actualizado Correctamente\"}";
	}

	@RequestMapping(value = "/referencia-personales", method = RequestMethod.DELETE)
	Map<String, String> eliminarReferenciaPersonal(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
	        Optional<ReferenciaPersonales> articulo = referenciasService.findById(id);
	        if(articulo.isPresent()) {
	        	referenciasService.delete(articulo.get());
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/referencia", method = RequestMethod.GET)
	public List<ReferenciaPersonales> consultaReferencia() {
		return referenciasService.findAll();
	}

	@RequestMapping(value = "/referencia", method = RequestMethod.POST)
	public String agregarReferencia(@RequestBody List<ReferenciaPersonales> listaReferencia) {
		referenciasService.saveAll(listaReferencia);
		return "SUCCESS";
	}

	@RequestMapping(value = "/domicilio", method = RequestMethod.DELETE)
	public String eliminarAllDomicilios() {
		referenciasService.deleteAll();
		return "SUCCESS";
	}

}
