package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.User;




public interface UserService {

	Optional<User> findById(Long id);

	User save(User referencia);

	void delete(User user);

	List<User> findAll();

	void saveAll(List<User> listaReferencia);

	void deleteAll();




}
