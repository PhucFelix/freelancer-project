package com.spring.security.repositories;

import com.spring.security.entities.ConfigDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfigDetailRepository extends JpaRepository<ConfigDetailEntity, Long> {
}
