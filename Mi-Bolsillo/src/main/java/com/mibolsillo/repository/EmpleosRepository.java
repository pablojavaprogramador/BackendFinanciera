package com.mibolsillo.repository;

import com.mibolsillo.domain.Empleos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Empleos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpleosRepository extends JpaRepository<Empleos, Long> {}
