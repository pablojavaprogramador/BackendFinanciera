package com.mibolsillo.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.mibolsillo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}