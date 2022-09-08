package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Compra;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;
import com.mibolsillo.repository.BitcoinRepository;
import com.mibolsillo.repository.CompraRepository;



@Service
public class CompraServiceImpl implements CompraService{

	@Autowired
	private CompraRepository compraRepository;

	@Override
	public Compra findById(Long id) {
		//Compra solicitud = compraRepository.findById(id).orElse(new Compra());
		return null;
	}

	@Override
	public RespuestaOk save(Compra compra) {
		try {
			compraRepository.save(compra);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(Compra compra) {
		try {
			compraRepository.save(compra);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(Compra compra) {
		compraRepository.delete(compra);

	}

	@Override
	public List<Compra> findAll() {
		return compraRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<Compra> compra) {
		compraRepository.saveAll(compra);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		compraRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
//		boolean referenciaid = compraRepository.existsById(id);
//		if (referenciaid == true) {
//
//			return true;
//		}
		return false;
	}

}
