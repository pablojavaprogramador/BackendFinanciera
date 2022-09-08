package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Cliente;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface ClienteService {
	Cliente findById(Long id);
	RespuestaOk save(Cliente cliente);
	void delete(Cliente cliente);
	List<Cliente> findAll();
	RespuestaOk saveAll(List<Cliente> cliente);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(Cliente cliente);

	
}
