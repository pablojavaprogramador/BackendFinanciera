package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.repository.DomicilioRepository;

@Service
public class DomicilioServiceImpl implements DomicilioService {
	
	@Autowired
	private  DomicilioRepository domicilioRepository ;
	@Override
	public Optional<Domicilio> findById(Long id) {
		domicilioRepository.findById(id);
		return Optional.empty();
	}
	@Override
	public Domicilio save(Domicilio articulo) {
		domicilioRepository.save(articulo);
		return null;
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
	public void saveAll(List<Domicilio> listaArticulos) {
		domicilioRepository.saveAll(listaArticulos);
		
	}

	@Override
	public void delete(Domicilio domicilio) {
		domicilioRepository.delete(domicilio);
		
	}


	
}
