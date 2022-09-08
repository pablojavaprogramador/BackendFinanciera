package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.repository.DomicilioRepository;

@Service
public class DomicilioServiceImpl implements DomicilioService {

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Override
	public Domicilio findById(Long id) {
		Domicilio domicilio = domicilioRepository.findById(id).orElse(new Domicilio());
		;

		return (Domicilio) domicilio;
	}

	@Override
	public RespuestaOk save(Domicilio articulo) {
		try {
		domicilioRepository.save(articulo);
		}catch(Exception e){
			
		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Domicilio Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk update(Domicilio articulo) {
		try {
		domicilioRepository.save(articulo);
		
		}catch(Exception e){
			
		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Domicilio Actualizado Correctamente");
		return respuesta;
	}
	
	@Override
	public List<Domicilio> findAll() {
		List<Domicilio> domicilios = domicilioRepository.findAll();
		return domicilios;
	}

	@Override
	public void deleteAll() {
		domicilioRepository.deleteAll();

	}

	@Override
	public RespuestaOk saveAll(List<Domicilio> listaArticulos) {
		try {
			List<Domicilio> domicilio = domicilioRepository.saveAll(listaArticulos);
		} catch (Exception e) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Domicilios Guardados Correctamente");
		return respuesta;

	}

	@Override
	public void delete(Domicilio domicilio) {
		domicilioRepository.delete(domicilio);

	}

	@Override
	public boolean existsById(Long id) {
		boolean existe = domicilioRepository.existsById(id);
		if(existe==true) {
			return existe;
		}
		return false;
	}

}
