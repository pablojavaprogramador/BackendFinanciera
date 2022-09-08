package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.dto.AdminUserDTO;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.User;

public interface UsuarioService {


	com.google.common.base.Optional<User> findById(Long id);

	RespuestaOk save(User referencia);

	void delete(User user);

	List<User> findAll();

	void saveAll(List<User> listaReferencia);

	void deleteAll();

	public Optional<User> activateRegistration(String key);

	public Optional<User> completePasswordReset(String newPassword, String key);

	public Optional<User> requestPasswordReset(String mail);

	public RespuestaOk registerUser(AdminUserDTO userDTO, String password);

	public User createUser(AdminUserDTO userDTO);

	public Optional<AdminUserDTO> updateUser(AdminUserDTO userDTO);

	public void deleteUser(String login);

	public void updateUser();

	public void changePassword(String currentClearTextPassword, String newPassword);

	boolean buscarUsuario(String usuario);


}
