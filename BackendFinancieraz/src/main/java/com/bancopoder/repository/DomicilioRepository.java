package com.bancopoder.repository;

import com.bancopoder.domain.Domicilio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Domicilio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {}
