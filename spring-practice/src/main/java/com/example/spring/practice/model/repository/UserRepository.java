package com.example.spring.practice.model.repository;


import com.example.spring.practice.model.repository.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    Iterable<UserEntity> findAllByCategory(String category);

    Iterable<UserEntity> findAllByCategoryOrderByName(String category);

    boolean existsByName(String name);

    long countByCategory(String category);

    Iterable<UserEntity> findByNameOrCategory(String name, String category);

    Iterable<UserEntity> findByNameStartsWith(String name);

    Stream<UserEntity> streamAllByCategory(String category);
}
