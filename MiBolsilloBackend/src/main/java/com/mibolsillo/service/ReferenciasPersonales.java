package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;




public interface ReferenciasPersonales {

	
	Optional<ReferenciaPersonales> findById(Long id);



	ReferenciaPersonales save(ReferenciaPersonales referencia);



	void delete(ReferenciaPersonales referenciasPersonales);



	List<ReferenciaPersonales> findAll();



	void saveAll(List<ReferenciaPersonales> listaReferencia);



	void deleteAll();

	

}
