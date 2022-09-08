package com.mibolsillo.repository;

import com.mibolsillo.model.TarjetasdeRegalo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TarjetasdeRegalo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TarjetasdeRegaloRepository extends JpaRepository<TarjetasdeRegalo, Long> {}
