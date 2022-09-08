package com.mibolsillo.repository;

import com.mibolsillo.model.SolicitudCredito;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SolicitudCredito entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SolicitudCreditoRepository extends JpaRepository<SolicitudCredito, Long> {}
