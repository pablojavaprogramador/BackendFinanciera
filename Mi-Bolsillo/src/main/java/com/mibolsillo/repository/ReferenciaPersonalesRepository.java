package com.mibolsillo.repository;

import com.mibolsillo.domain.ReferenciaPersonales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ReferenciaPersonales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferenciaPersonalesRepository extends JpaRepository<ReferenciaPersonales, Long> {}
