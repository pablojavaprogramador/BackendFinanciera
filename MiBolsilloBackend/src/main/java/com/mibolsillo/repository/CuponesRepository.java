package com.mibolsillo.repository;

import com.mibolsillo.model.Cupones;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Cupones entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CuponesRepository extends JpaRepository<Cupones, Long> {}
