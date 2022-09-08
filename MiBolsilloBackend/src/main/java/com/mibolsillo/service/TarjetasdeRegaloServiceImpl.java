package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;
import com.mibolsillo.repository.TarjetasdeRegaloRepository;

@Service
public class TarjetasdeRegaloServiceImpl implements TarjetasdeRegaloService {

	@Autowired
	private TarjetasdeRegaloRepository tarjetasdeRegalo;

	@Override
	public TarjetasdeRegalo findById(Long id) {
		TarjetasdeRegalo referencia = tarjetasdeRegalo.findById(id).orElse(new TarjetasdeRegalo());
		return referencia;
	}

	@Override
	public RespuestaOk save(TarjetasdeRegalo tarjetasRegalo) {
		try {
			tarjetasdeRegalo.save(tarjetasRegalo);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(TarjetasdeRegalo tarjetasRegalo) {
		try {
			tarjetasdeRegalo.save(tarjetasRegalo);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(TarjetasdeRegalo tarjetasRegalo) {
		tarjetasdeRegalo.delete(tarjetasRegalo);

	}

	@Override
	public List<TarjetasdeRegalo> findAll() {
		return tarjetasdeRegalo.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<TarjetasdeRegalo> tarjetasRegalo) {
		tarjetasdeRegalo.saveAll(tarjetasRegalo);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		tarjetasdeRegalo.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = tarjetasdeRegalo.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
