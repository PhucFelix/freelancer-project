package com.spring.security.info;

import com.spring.security.entities.ConfigDetailEntity;
import com.spring.security.repositories.ConfigDetailRepository;
import com.spring.security.services.ConfigDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Endpoint(id = "config")
public class ConfigDetaltContributorEndPoint {

    @Autowired
    private ConfigDetailService configDetailService;


    @Autowired
    private ConfigDetailRepository configDetailRepository;

    @ReadOperation
    public Iterable<ConfigDetailEntity> config() {
        return configDetailService.getAvailableCourses();
    }

    @ReadOperation
    public Object selectCourse(@Selector Long courseId) {
        Iterable<ConfigDetailEntity> courses = configDetailRepository.findAll();
        for (ConfigDetailEntity course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return String.format("No course with course id %d available", courseId);
    }

    @WriteOperation
    public void updateCourseRating(@Selector Long courseId, int newRating) {
        Optional<ConfigDetailEntity> optionalCourse = configDetailRepository.findById(courseId);
        if (!optionalCourse.isEmpty()) {
            ConfigDetailEntity course = optionalCourse.get();
            course.setRating(newRating);
            configDetailRepository.save(course);
        }
    }

    @DeleteOperation
    public void deleteCourses() {
        configDetailRepository.deleteAll();
    }

    @DeleteOperation
    public void deleteCoursebyId(@Selector Long courseId) {
        configDetailRepository.deleteById(courseId);
    }
}