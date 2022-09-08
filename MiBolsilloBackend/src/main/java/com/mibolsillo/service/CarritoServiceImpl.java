package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Carrito;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.BitcoinRepository;
import com.mibolsillo.repository.CarritoRepository;



@Service
public class CarritoServiceImpl implements CarritoService{

	@Autowired
	private CarritoRepository carritoRepository;

	@Override
	public Carrito findById(Long id) {
		//Carrito solicitud = carritoRepository.findById(id).orElse(new Carrito());
		return null;
	}

	@Override
	public RespuestaOk save(Carrito carrito) {
		try {
			carritoRepository.save(carrito);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(Carrito carrito) {
		try {
			carritoRepository.save(carrito);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(Carrito carrito) {
		carritoRepository.delete(carrito);

	}

	@Override
	public List<Carrito> findAll() {
		return carritoRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<Carrito> carrito) {
		carritoRepository.saveAll(carrito);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		carritoRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
//		boolean referenciaid = carritoRepository.existsById(id);
//		if (referenciaid == true) {
//
//			return true;
//		}
		return false;
	}

}
