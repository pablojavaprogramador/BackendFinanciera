package com.mibolsillo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.mibolsillo.dto.AdminUserDTO;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.User;
import com.mibolsillo.repository.UserRepository;






public interface UserService {

	Optional<User> findById(Long id);

	User save(User referencia);

	void delete(User user);

	List<User> findAll();

	void saveAll(List<User> listaReferencia);

	void deleteAll();


	
    public Optional<User> activateRegistration(String key) ;

    public Optional<User> completePasswordReset(String newPassword, String key) ;

    public Optional<User> requestPasswordReset(String mail) ;

    public User registerUser(AdminUserDTO userDTO, String password) ;
       
    public User createUser(AdminUserDTO userDTO) ;
 
    public Optional<AdminUserDTO> updateUser(AdminUserDTO userDTO) ;

    public void deleteUser(String login);

    public void updateUser ();
 
    public void changePassword(String currentClearTextPassword, String newPassword);
    
 
    

}
