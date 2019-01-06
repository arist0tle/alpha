package com.geektcp.alpha.spring.jpa.repository;

import com.geektcp.alpha.spring.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<User, Long> {

}
