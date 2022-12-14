package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;

@Service
public class ReferenciasPersonalesImpl implements ReferenciasPersonales {

	@Autowired
	private ReferenciaPersonalesRepository referenciaRepositorio;

	@Override
	public ReferenciaPersonales findById(Long id) {
		ReferenciaPersonales referencia = referenciaRepositorio.findById(id).orElse(new ReferenciaPersonales());
		return referencia;
	}

	@Override
	public RespuestaOk save(ReferenciaPersonales referencia) {
		try {
			referenciaRepositorio.save(referencia);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}
	
	@Override
	public RespuestaOk actualizar(ReferenciaPersonales referencia) {
		try {
			referenciaRepositorio.save(referencia);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	

	@Override
	public void delete(ReferenciaPersonales referenciasPersonales) {
		referenciaRepositorio.delete(referenciasPersonales);

	}

	@Override
	public List<ReferenciaPersonales> findAll() {
		return referenciaRepositorio.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<ReferenciaPersonales> listaReferencia) {
		referenciaRepositorio.saveAll(listaReferencia);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		referenciaRepositorio.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = referenciaRepositorio.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
