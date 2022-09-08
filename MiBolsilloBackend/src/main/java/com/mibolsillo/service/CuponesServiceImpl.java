package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.CuponesRepository;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;
import com.mibolsillo.repository.SolicitudCreditoRepository;
import com.mibolsillo.repository.TarjetasdeRegaloRepository;

@Service
public class CuponesServiceImpl implements CuponesService {

	@Autowired
	private CuponesRepository cuponesRepository;

	@Override
	public Cupones findById(Long id) {
		Cupones solicitud = cuponesRepository.findById(id).orElse(new Cupones());
		return solicitud;
	}

	@Override
	public RespuestaOk save(Cupones cupones) {
		try {
			cuponesRepository.save(cupones);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(Cupones cupones) {
		try {
			cuponesRepository.save(cupones);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(Cupones cupones) {
		cuponesRepository.delete(cupones);

	}

	@Override
	public List<Cupones> findAll() {
		return cuponesRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<Cupones> cupones) {
		cuponesRepository.saveAll(cupones);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		cuponesRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = cuponesRepository.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
