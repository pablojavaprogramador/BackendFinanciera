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
import com.mibolsillo.model.User;
import com.mibolsillo.model.User;
import com.mibolsillo.service.DomicilioService;

import com.mibolsillo.service.UserService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class UsersController {

	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	Optional<User> consultarUsers(@PathVariable Long id) {
		return userService.findById(id);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/usuarios", method = RequestMethod.POST)
	public String agregarUsers(@RequestBody User user) {
		User salvarDomicilio = userService.save(user);

		return "{\"mensaje\":\"Users Guardada Correctamente\"}";
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.PUT)
	public String actualizarDomicilio(@RequestBody User user) {
		User actualizarDomicilio= userService.save(user);
		 	return "{\"mensaje\":\"Users  Actualizado Correctamente\"}";
	}

	@RequestMapping(value = "/eliminarAllUsuarios", method = RequestMethod.DELETE)
	Map<String, String> eliminarUsers(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
	        Optional<User> articulo = userService.findById(id);
	        if(articulo.isPresent()) {
	        	userService.delete(articulo.get());
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public List<User> consultaUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public String agregarUsers(@RequestBody List<User> listaUsers) {
		userService.saveAll(listaUsers);
		return "SUCCESS";
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.DELETE)
	public String eliminarAllUsers() {
		userService.deleteAll();
		return "SUCCESS";
	}

}
