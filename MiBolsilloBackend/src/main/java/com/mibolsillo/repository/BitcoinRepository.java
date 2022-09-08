package com.mibolsillo.repository;

import com.mibolsillo.model.Bitcoin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Bitcoin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {}
