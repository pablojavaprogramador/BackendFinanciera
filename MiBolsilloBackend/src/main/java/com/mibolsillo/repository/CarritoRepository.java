package com.mibolsillo.repository;

import com.mibolsillo.model.Carrito;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Carrito entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarritoRepository extends JpaRepository<Carrito, String> {}
