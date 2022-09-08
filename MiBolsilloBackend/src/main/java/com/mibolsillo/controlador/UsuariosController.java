package com.mibolsillo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bolsillo.model.error.YaRegistradoException;
import com.google.common.base.Optional;

import com.mibolsillo.model.ManagedUserVM;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.User;
import com.mibolsillo.service.UsuarioService;



/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class UsuariosController {

	 //private final Logger log = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UsuarioService userService;

	
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	Optional<User> consultarUsers(@PathVariable Long id) {
		return userService.findById(id);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/usuarios", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarUsers(@Valid @RequestBody ManagedUserVM managedUserVM) {
	//	log.info("Datos de Usuario"+managedUserVM.toString());
	
		if(managedUserVM.getLogin().equals(null)||managedUserVM.getLogin().equals(null)){
			throw new YaRegistradoException("Usuario invalido o ya registrado " + managedUserVM.getId());
		}
		
	//	boolean referencia = userService.buscarUsuario(managedUserVM.getLogin());
//		
//		if (referencia==true ) {
//
//			throw new YaRegistradoException("Usuario ya registrado: " + managedUserVM.getId());
//		}
//		

		RespuestaOk user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
		return new ResponseEntity<RespuestaOk>(user,HttpStatus.CREATED);
	}                                                                                                                                                                                    

	@RequestMapping(value = "/usuarios", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarDomicilio(@RequestBody User user) {
		RespuestaOk actualizarUsuarios= userService.save(user);
		 	return new ResponseEntity<RespuestaOk>(actualizarUsuarios,HttpStatus.OK);
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

	@RequestMapping(value = "/usuariosagregar", method = RequestMethod.POST)
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
