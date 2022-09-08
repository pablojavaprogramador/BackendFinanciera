package com.mibolsillo.controlador;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.bolsillo.model.error.BadRequestAlertException;
import com.bolsillo.model.error.ModeloNotFoundException;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.TarjetasdeRegaloRepository;
import com.mibolsillo.service.ReferenciasPersonales;
import com.mibolsillo.service.TarjetasdeRegaloService;

@RestController
@RequestMapping("/apis")
@Transactional
public class TarjetasdeRegaloController {

	@Autowired
	private TarjetasdeRegaloService tarjetasService;

	@RequestMapping(value = "/tarjetasde-regalos/{id}", method = RequestMethod.GET)
	ResponseEntity<TarjetasdeRegalo> tarjetaRegalo(@PathVariable Long id) {
		TarjetasdeRegalo tarjetasdeRegalo = tarjetasService.findById(id);

		if (id == null) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}

		boolean referencia = tarjetasService.existsById(id);

		if (referencia == false) {

			throw new ModeloNotFoundException("ID No encontrado: " + id);
		}

		return new ResponseEntity<TarjetasdeRegalo>(tarjetasdeRegalo, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/tarjetasde-regalos", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> agregarReferencia(@RequestBody TarjetasdeRegalo tarjeta) {
		RespuestaOk respuestaOk = tarjetasService.save(tarjeta);

		return new ResponseEntity<RespuestaOk>(respuestaOk, HttpStatus.OK);
	}

	@RequestMapping(value = "/tarjetasde-regalos", method = RequestMethod.PUT)
	public ResponseEntity<RespuestaOk> actualizarDomicilio(@RequestBody TarjetasdeRegalo tarjeta) {
		
		if (tarjeta.getId() == null) {
            throw new BadRequestAlertException("Id Invalido");
        }
  
        if (tarjetasService.existsById(tarjeta.getId())==false) {
        	throw new BadRequestAlertException("No existe el id a actualizar");
        }
        
		RespuestaOk actualizarDomicilio = tarjetasService.actualizar(tarjeta);

		return new ResponseEntity<RespuestaOk>(actualizarDomicilio, HttpStatus.OK);
	}

	@RequestMapping(value = "/tarjetasde-regalos", method = RequestMethod.DELETE)
	Map<String, String> eliminarReferenciaPersonal(@RequestParam Long id) {
		Map<String, String> status = new HashMap<>();
		TarjetasdeRegalo tarjeta = tarjetasService.findById(id);
		boolean tarjetaid = tarjetasService.existsById(id);
		if (tarjetaid == true) {
			tarjetasService.delete(tarjeta);
			status.put("Estatus", "Se elimino el domicilio correctamente");
		} else {
			status.put("Estatus", "El Domicilio no se ha encontrado");
		}
		return status;
	}

	@RequestMapping(value = "/tarjetasde-regalos", method = RequestMethod.GET)
	public ResponseEntity<List<TarjetasdeRegalo>> consultaReferencia() {
		List<TarjetasdeRegalo> tarjeta = tarjetasService.findAll();
		return new ResponseEntity<List<TarjetasdeRegalo>>(tarjeta, HttpStatus.OK);
	}

	@RequestMapping(value = "/tarjetasde-regalosall", method = RequestMethod.POST)
	public ResponseEntity<RespuestaOk> salvarReferencias(@RequestBody List<TarjetasdeRegalo> listaReferencia) {
		RespuestaOk tarjeta = tarjetasService.saveAll(listaReferencia);
		return new ResponseEntity<RespuestaOk>(tarjeta, HttpStatus.OK);
	}

	@RequestMapping(value = "/tarjetasde-regalos/eliminar", method = RequestMethod.DELETE)
	public String eliminarAllDomicilios() {
		tarjetasService.deleteAll();
		return "SUCCESS";
	}
}
