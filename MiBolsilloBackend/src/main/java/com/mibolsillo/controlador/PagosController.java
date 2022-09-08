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
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
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
public class PagosController {

	
	@Autowired
	private PagosService pagosService;

	
	@RequestMapping(value = "/pagode-servicios/{id}", method = RequestMethod.GET)
	ResponseEntity<PagodeServicios> consultarCupones(@PathVariable Long id) {
		PagodeServicios pagos=pagosService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = pagosService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<PagodeServicios>(pagos,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/pagode-servicios", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarCupones(@RequestBody PagodeServicios pagos) {
		RespuestaOk respuestaOk = pagosService.save(pagos);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/pagode-servicios", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarCupones(@RequestBody PagodeServicios pagos) {
		
		if (pagos.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (pagosService.existsById(pagos.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
        
		RespuestaOk actualizarDomicilio= pagosService.actualizar(pagos);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/pagode-servicios", method = RequestMethod.DELETE)
	Map<String, String> eliminarCupones(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		PagodeServicios pagos = pagosService.findById(id);
	    	boolean referenciaid = pagosService.existsById(id);
	        if(referenciaid==true) {
	        	pagosService.delete(pagos);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/pagode-servicios", method = RequestMethod.GET)
	public ResponseEntity<List<PagodeServicios>> consultaCupones() {
		List<PagodeServicios> pagos = pagosService.findAll();
		return new ResponseEntity<List<PagodeServicios>>(pagos,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/pagode-serviciosall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarCupones(@RequestBody List<PagodeServicios> listasolicitudpagos) {
		RespuestaOk pagos=pagosService.saveAll(listasolicitudpagos);
		return new ResponseEntity<RespuestaOk>(pagos,HttpStatus.OK);
	}

	@RequestMapping(value = "/pagode-servicios/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllCupones() {
		pagosService.deleteAll();
		return "SUCCESS";
	}

}
