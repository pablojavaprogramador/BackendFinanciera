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
import com.mibolsillo.model.Carrito;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.service.BitcoinService;
import com.mibolsillo.service.CarritoService;
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
public class CarritoController {

	
	@Autowired
	private CarritoService carritoService;

	
	@RequestMapping(value = "/carrito/{id}", method = RequestMethod.GET)
	ResponseEntity<Carrito> consultarCupones(@PathVariable Long id) {
		Carrito carrito=carritoService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = carritoService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<Carrito>(carrito,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/carrito", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarCupones(@RequestBody Carrito carrito) {
		RespuestaOk respuestaOk = carritoService.save(carrito);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/carrito", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarCupones(@RequestBody Carrito carrito) {
		
		if (carrito.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
//        if (carritoService.existsById(carrito.getId())==false) {
//        	throw new BadRequestAlertException("No existe el id a actualizar");
//        }
//        
        
		RespuestaOk actualizarDomicilio= carritoService.actualizar(carrito);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/carrito", method = RequestMethod.DELETE)
	Map<String, String> eliminarCupones(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		Carrito bitcoin = carritoService.findById(id);
	    	boolean referenciaid = carritoService.existsById(id);
	        if(referenciaid==true) {
	        	carritoService.delete(bitcoin);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/carrito", method = RequestMethod.GET)
	public ResponseEntity<List<Carrito>> consultaCupones() {
		List<Carrito> carrito = carritoService.findAll();
		return new ResponseEntity<List<Carrito>>(carrito,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/carrito", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarCupones(@RequestBody List<Carrito> listacarrito) {
		RespuestaOk carrito=carritoService.saveAll(listacarrito);
		return new ResponseEntity<RespuestaOk>(carrito,HttpStatus.OK);
	}

	@RequestMapping(value = "/carrito/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllCupones() {
		carritoService.deleteAll();
		return "SUCCESS";
	}

}
