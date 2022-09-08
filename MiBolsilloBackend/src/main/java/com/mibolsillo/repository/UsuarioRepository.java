package com.mibolsillo.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mibolsillo.model.User;


@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {
//
	@Query(value="SELECT p FROM User p WHERE p.login like %:usuario%")
	boolean buscarUsuario(@Param("usuario") String usuario);

}