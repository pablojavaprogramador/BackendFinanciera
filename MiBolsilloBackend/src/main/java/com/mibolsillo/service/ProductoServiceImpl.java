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
import com.mibolsillo.repository.CuponesRepository;
import com.mibolsillo.repository.PagodeServiciosRepository;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;
import com.mibolsillo.repository.SolicitudCreditoRepository;
import com.mibolsillo.repository.TarjetasdeRegaloRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private PagodeServiciosRepository pagodeServiciosRepository;

	@Override
	public Producto findById(Long id) {
		//Producto solicitud = pagodeServiciosRepository.findById(id).orElse(new Producto());
		return null;
	}

	@Override
	public RespuestaOk save(Producto pagodeServicios) {
		try {
			//pagodeServiciosRepository.save(pagodeServicios);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(Producto pagodeServicios) {
		try {
		//	pagodeServiciosRepository.save(pagodeServicios);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(Producto pagodeServicios) {
		//pagodeServiciosRepository.delete(pagodeServicios);

	}

	@Override
	public List<Producto> findAll() {
		return null;
	//	return pagodeServiciosRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<Producto> pagodeServicios) {
		//pagodeServiciosRepository.saveAll(pagodeServicios);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		pagodeServiciosRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = pagodeServiciosRepository.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
