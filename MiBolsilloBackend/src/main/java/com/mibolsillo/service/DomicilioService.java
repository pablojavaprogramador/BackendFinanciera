package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;




public interface DomicilioService {

	Optional<Domicilio> findById(Long id);


	Domicilio save(Domicilio articulo);

	List<Domicilio> findAll();

	void deleteAll();

	void saveAll(List<Domicilio> listaArticulos);


	void delete(Domicilio domicilio);

	


}
