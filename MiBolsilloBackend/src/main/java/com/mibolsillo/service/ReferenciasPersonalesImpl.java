package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.repository.ReferenciaPersonalesRepository;



@Service
public class ReferenciasPersonalesImpl implements ReferenciasPersonales{

	@Autowired
	private ReferenciaPersonalesRepository referenciaRepositorio;
	
	@Override
	public Optional<ReferenciaPersonales> findById(Long id) {
		return referenciaRepositorio.findById(id);
	}

	@Override
	public ReferenciaPersonales save(ReferenciaPersonales referencia) {
	
		referenciaRepositorio.save(referencia);
		return null;
	}

	@Override
	public void delete(ReferenciaPersonales referenciasPersonales) {
		referenciaRepositorio.delete(referenciasPersonales);
		
	}

	@Override
	public List<ReferenciaPersonales> findAll() {
		return referenciaRepositorio.findAll();
	}

	@Override
	public void saveAll(List<ReferenciaPersonales> listaReferencia) {
		referenciaRepositorio.saveAll(listaReferencia);
		
	}

	@Override
	public void deleteAll() {
		referenciaRepositorio.deleteAll();
		
	}

	

}
