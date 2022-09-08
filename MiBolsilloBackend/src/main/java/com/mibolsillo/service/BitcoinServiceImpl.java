package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.BitcoinRepository;



@Service
public class BitcoinServiceImpl implements BitcoinService{

	@Autowired
	private BitcoinRepository bitcoinRepository;

	@Override
	public Bitcoin findById(Long id) {
		Bitcoin solicitud = bitcoinRepository.findById(id).orElse(new Bitcoin());
		return solicitud;
	}

	@Override
	public RespuestaOk save(Bitcoin bitcoin) {
		try {
			bitcoinRepository.save(bitcoin);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(Bitcoin bitcoin) {
		try {
			bitcoinRepository.save(bitcoin);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(Bitcoin bitcoin) {
		bitcoinRepository.delete(bitcoin);

	}

	@Override
	public List<Bitcoin> findAll() {
		return bitcoinRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<Bitcoin> bitcoin) {
		bitcoinRepository.saveAll(bitcoin);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		bitcoinRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = bitcoinRepository.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
