package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.RespuestaOk;




public interface EmpleoService {

	Empleos findById(Long id);


	RespuestaOk save(Empleos empleo);
	RespuestaOk actualizar(Empleos empleo);

	List<Empleos> findAll();

	void deleteAll();

	void saveAll(List<Empleos> listEmpleos);


	void delete(Empleos empleos);


	boolean existsById(Long id);

	


}
