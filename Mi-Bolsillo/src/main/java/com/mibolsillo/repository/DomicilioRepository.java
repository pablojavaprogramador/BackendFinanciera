package com.mibolsillo.repository;

import com.mibolsillo.domain.Domicilio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Domicilio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {}
