package com.mibolsillo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Cliente;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.repository.ClienteRepository;



@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente findById(Long id) {
		Cliente solicitud = clienteRepository.findById(id).orElse(new Cliente());
		return solicitud;
	}

	@Override
	public RespuestaOk save(Cliente cliente) {
		try {
			clienteRepository.save(cliente);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
	}

	@Override
	public RespuestaOk actualizar(Cliente cliente) {
		try {
			clienteRepository.save(cliente);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void delete(Cliente cliente) {
		clienteRepository.delete(cliente);

	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public RespuestaOk saveAll(List<Cliente> cliente) {
		clienteRepository.saveAll(cliente);

		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Actualizada Correctamente");
		return respuesta;
	}

	@Override
	public void deleteAll() {
		clienteRepository.deleteAll();

	}

	@Override
	public boolean existsById(Long id) {
		boolean referenciaid = clienteRepository.existsById(id);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}

}
