package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.Producto;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.PagodeServiciosRepository;

@Service
public class PagosServiceImpl implements PagosService {

	@Autowired
	private PagodeServiciosRepository pagoServiciosRepository;

	@Override
	public PagodeServicios findById(Long id) {
		PagodeServicios solicitud = pagoServiciosRepository.findById(id).orElse(new PagodeServicios());
		return null;
	}

	@Override
	public RespuestaOk save(PagodeServicios pago) {
		try {
			pagoServiciosRepository.save(pago);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(PagodeServicios pago) {
		try {
			pagoServiciosRepository.save(pago);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(PagodeServicios pago) {
		pagoServiciosRepository.delete(pago);

	}

	@Override
	public List<PagodeServicios> findAll() {
		return pagoServiciosRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<PagodeServicios> pago) {
		pagoServiciosRepository.saveAll(pago);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		pagoServiciosRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = pagoServiciosRepository.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
