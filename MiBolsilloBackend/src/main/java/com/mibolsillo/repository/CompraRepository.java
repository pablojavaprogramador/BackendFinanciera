package com.mibolsillo.repository;

import com.mibolsillo.model.Compra;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Compra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompraRepository extends JpaRepository<Compra, String> {}
