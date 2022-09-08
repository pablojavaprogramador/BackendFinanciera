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
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.service.DomicilioService;
import com.mibolsillo.service.ReferenciasPersonales;
import com.mibolsillo.service.SolicitudCreditoService;

/**
 * REST controller
 */
@RestController
@RequestMapping(value="/apis")
public class SolicitudCreditoController {

	
	@Autowired
	private SolicitudCreditoService solicititudCreditoService;

	
	@RequestMapping(value = "/solicitud-creditos/{id}", method = RequestMethod.GET)
	ResponseEntity<SolicitudCredito> consultarReferencia(@PathVariable Long id) {
		SolicitudCredito solicitudCredito=solicititudCreditoService.findById(id);
		
		
		if (id == null ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		boolean referencia = solicititudCreditoService.existsById(id);

		if (referencia==false ) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}
		
		
		return new ResponseEntity<SolicitudCredito>(solicitudCredito,HttpStatus.OK) ;
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/solicitud-creditos", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarReferencia(@RequestBody SolicitudCredito solicitudCredito) {
		RespuestaOk respuestaOk = solicititudCreditoService.save(solicitudCredito);

		return new ResponseEntity<RespuestaOk>(respuestaOk,HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value = "/solicitud-creditos", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarDomicilio(@RequestBody SolicitudCredito solicitudCredito) {
		
		if (solicitudCredito.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (solicititudCreditoService.existsById(solicitudCredito.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
        
		RespuestaOk actualizarDomicilio= solicititudCreditoService.actualizar(solicitudCredito);
		
		 	return new ResponseEntity<RespuestaOk>(actualizarDomicilio,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/solicitud-creditos", method = RequestMethod.DELETE)
	Map<String, String> eliminarReferenciaPersonal(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		SolicitudCredito solicitudCredito = solicititudCreditoService.findById(id);
	    	boolean referenciaid = solicititudCreditoService.existsById(id);
	        if(referenciaid==true) {
	        	solicititudCreditoService.delete(solicitudCredito);
	            status.put("Estatus", "Se elimino el domicilio correctamente");
	        }
	        else {
	            status.put("Estatus", "El Domicilio no se ha encontrado");
	        } 
		return status;
	}




	@RequestMapping(value = "/solicitud-creditos", method = RequestMethod.GET)
	public ResponseEntity<List<SolicitudCredito>> consultaReferencia() {
		List<SolicitudCredito> solicitudCredito = solicititudCreditoService.findAll();
		return new ResponseEntity<List<SolicitudCredito>>(solicitudCredito,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/solicitud-creditosall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk>salvarReferencias(@RequestBody List<SolicitudCredito> listasolicitudCredito) {
		RespuestaOk solicitud=solicititudCreditoService.saveAll(listasolicitudCredito);
		return new ResponseEntity<RespuestaOk>(solicitud,HttpStatus.OK);
	}

	@RequestMapping(value = "/solicitud-creditos/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllDomicilios() {
		solicititudCreditoService.deleteAll();
		return "SUCCESS";
	}

}
