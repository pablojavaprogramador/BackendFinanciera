package com.mibolsillo.repository;

import com.mibolsillo.model.PagodeServicios;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PagodeServicios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PagodeServiciosRepository extends JpaRepository<PagodeServicios, Long> {}
