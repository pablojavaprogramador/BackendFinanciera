package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Domicilio;

import com.mibolsillo.model.User;
import com.mibolsillo.repository.UserRepository;



@Service
public class  UserServiceImpl implements  UserService{

	@Autowired
	private UserRepository repositorioUser;
	
	@Override
	public Optional<User> findById(Long id) {
		repositorioUser.findById(id);
		return Optional.empty();
	}

	@Override
	public User save(User referencia) {
		repositorioUser.save(referencia);
		return null;
	}

	@Override
	public void delete(User user) {
		repositorioUser.delete(user);
		
	}

	@Override
	public List<User> findAll() {
		return repositorioUser.findAll();
	}

	@Override
	public void saveAll(List<User> listaReferencia) {
		repositorioUser.saveAll(listaReferencia);
		
	}

	@Override
	public void deleteAll() {
		repositorioUser.deleteAll();
		
	}

	


}
