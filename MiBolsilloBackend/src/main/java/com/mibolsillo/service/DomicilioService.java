package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.RespuestaOk;




public interface DomicilioService {

	Domicilio findById(Long id);


	RespuestaOk save(Domicilio articulo);
	RespuestaOk update(Domicilio articulo);

	List<Domicilio> findAll();

	void deleteAll();

	RespuestaOk saveAll(List<Domicilio> listaArticulos);


	void delete(Domicilio domicilio);


	boolean existsById(Long id);

	


}
