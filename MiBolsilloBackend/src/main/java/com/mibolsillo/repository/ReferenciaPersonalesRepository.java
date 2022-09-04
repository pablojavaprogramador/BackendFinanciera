package com.mibolsillo.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.mibolsillo.model.ReferenciaPersonales;

/**
 * Spring Data SQL repository for the ReferenciaPersonales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferenciaPersonalesRepository extends JpaRepository<ReferenciaPersonales, Long> {}
