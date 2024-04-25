package com.spring.security.services;

import com.spring.security.entities.ConfigDetailEntity;
import com.spring.security.repositories.ConfigDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigDetailService {
    private final ConfigDetailRepository courseRepository;

    @Autowired
    public ConfigDetailService(ConfigDetailRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Iterable<ConfigDetailEntity> getAvailableCourses() {
        return courseRepository.findAll();
    }
}
