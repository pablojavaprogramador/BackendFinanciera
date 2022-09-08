package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;
import com.mibolsillo.repository.SolicitudCreditoRepository;
import com.mibolsillo.repository.TarjetasdeRegaloRepository;

@Service
public class SolicitudCreditoServiceImpl implements SolicitudCreditoService {

	@Autowired
	private SolicitudCreditoRepository solcitidudCreditoRepository;

	@Override
	public SolicitudCredito findById(Long id) {
		SolicitudCredito solicitud = solcitidudCreditoRepository.findById(id).orElse(new SolicitudCredito());
		return solicitud;
	}

	@Override
	public RespuestaOk save(SolicitudCredito solicitud) {
		try {
			solcitidudCreditoRepository.save(solicitud);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(SolicitudCredito solicitud) {
		try {
			solcitidudCreditoRepository.save(solicitud);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(SolicitudCredito solicitud) {
		solcitidudCreditoRepository.delete(solicitud);

	}

	@Override
	public List<SolicitudCredito> findAll() {
		return solcitidudCreditoRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<SolicitudCredito> solicitud) {
		solcitidudCreditoRepository.saveAll(solicitud);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		solcitidudCreditoRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = solcitidudCreditoRepository.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
