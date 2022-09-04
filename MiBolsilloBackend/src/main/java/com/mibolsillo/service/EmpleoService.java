package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;




public interface EmpleoService {

	Optional<Empleos> findById(Long id);


	Empleos save(Empleos empleo);

	List<Empleos> findAll();

	void deleteAll();

	void saveAll(List<Empleos> listEmpleos);


	void delete(Empleos empleos);

	


}
