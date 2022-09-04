package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.repository.EmpleosRepository;



@Service
public class EmpleoServiceImpl  implements EmpleoService{

	@Autowired
	EmpleosRepository repositoryEmpleo;
	
	@Override
	public Optional<Empleos> findById(Long id) {
		repositoryEmpleo.findById(id);
		return Optional.empty();
	}

	@Override
	public Empleos save(Empleos empleo) {
		repositoryEmpleo.save(empleo);
		return null;
	}

	@Override
	public List<Empleos> findAll() {
		List<Empleos> empleos = repositoryEmpleo.findAll();
		return empleos;
	}

	@Override
	public void deleteAll() {
		repositoryEmpleo.deleteAll();

		
	}

	@Override
	public void saveAll(List<Empleos> listEmpleos) {
		repositoryEmpleo.saveAll(listEmpleos);
		
	}

	@Override
	public void delete(Empleos empleos) {
	repositoryEmpleo.delete(empleos);
		
	}

}
