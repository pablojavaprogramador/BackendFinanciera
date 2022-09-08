package com.mibolsillo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mibolsillo.model.RespuestaOk;

import com.mibolsillo.dto.AdminUserDTO;
import com.mibolsillo.model.Authority;
import com.mibolsillo.model.User;
import com.mibolsillo.repository.AuthorityRepository;
import com.mibolsillo.repository.UsuarioRepository;
import com.mibolsillo.utils.AuthoritiesConstants;

@Service
public  class  UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repositorioUser;
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public com.google.common.base.Optional<User> findById(Long id) {
		repositorioUser.findById(id);
		return null;
	}

	@Override
	public RespuestaOk save(User referencia) {
		
		try {
			repositorioUser.save(referencia);
		} catch (Exception ex) {

		}
		RespuestaOk respuesta = new RespuestaOk();
		respuesta.setEstatus("OK");
		respuesta.setMensaje("Referencia Guardado Correctamente");
		return respuesta;
		
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

	@Override
	public Optional<User> activateRegistration(String key) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> completePasswordReset(String newPassword, String key) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> requestPasswordReset(String mail) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public RespuestaOk registerUser(AdminUserDTO userDTO, String password) {
		 User newUser = new User();
	        String encryptedPassword = password.toString();
	        newUser.setLogin(userDTO.getLogin().toLowerCase());
	        // new user gets initially a generated password
	        newUser.setPassword(encryptedPassword);
	        newUser.setFirstName(userDTO.getFirstName());
	        newUser.setLastName(userDTO.getLastName());
	        if (userDTO.getEmail() != null) {
	            newUser.setEmail(userDTO.getEmail().toLowerCase());
	        }
	        newUser.setImageUrl(userDTO.getImageUrl());
	        newUser.setLangKey(userDTO.getLangKey());
	        // new user is not active
	        newUser.setActivated(true);
	        // new user gets registration key
	        //newUser.setActivationKey(RandomUtil.generateActivationKey());
	        Set<Authority> authorities = new HashSet<>();
	        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
	      //  Authority autorizacionhes=new Authority();
	      //  autorizacionhes.setName("dsadaddsadsa");
	       // authorities.add(autorizacionhes);
	        newUser.setAuthorities(authorities);
	        repositorioUser.save(newUser);
	        //this.clearUserCaches(newUser);
	     //   log.debug("Created Information for User: {}", newUser);
	   
			RespuestaOk respuesta = new RespuestaOk();
			respuesta.setEstatus("OK");
			respuesta.setMensaje("Referencia Guardado Correctamente");
			return respuesta;
			
	      
	}

	@Override
	public User createUser(AdminUserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AdminUserDTO> updateUser(AdminUserDTO userDTO) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteUser(String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String currentClearTextPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}


//	@Override
//	public boolean existsById(Long id) {
//		boolean referenciaid = repositorioUser.existsById(id);
//		if (referenciaid == true) {
//
//			return true;
//		}
//		return false;
//	}

	

	@Override
	public boolean buscarUsuario(String usuario) {
		boolean referenciaid = repositorioUser.buscarUsuario(usuario);
		if (referenciaid == true) {

			return true;
		}
		return false;
	}


}
