package com.mibolsillo.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.mibolsillo.model.Empleos;

/**
 * Spring Data SQL repository for the Empleos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpleosRepository extends JpaRepository<Empleos, Long> {}
