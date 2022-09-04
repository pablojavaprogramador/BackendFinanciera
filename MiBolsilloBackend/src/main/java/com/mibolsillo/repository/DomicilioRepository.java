package com.mibolsillo.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.mibolsillo.model.Domicilio;


@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {

}