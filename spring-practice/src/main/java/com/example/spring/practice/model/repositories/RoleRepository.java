package com.example.spring.practice.model.repositories;


import com.example.spring.practice.model.entities.RoleEntiy;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntiy, Long> {

}
