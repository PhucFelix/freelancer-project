package com.spring.security.info;

import com.spring.security.entities.ConfigDetailEntity;
import com.spring.security.services.ConfigDetailService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CourseInfoContributor implements InfoContributor {

    private ConfigDetailService configDetailService;

    @Autowired
    public CourseInfoContributor(ConfigDetailService courseService) {
        this.configDetailService = courseService;
    }

    public void contribute(Info.Builder builder) {
        Map<String, Integer> courseNameRatingMap = new HashMap<>();
        List<CourseNameRating> configDetalList = new ArrayList<>();
        for(ConfigDetailEntity course : configDetailService.getAvailableCourses()) {
            configDetalList.add(CourseNameRating.builder().name(course.getName()).rating(course.getRating()).build());
        }
        builder.withDetail("configDetail", configDetalList);
    }

    @Builder
    @Data
    private static class CourseNameRating {
        String name;
        int rating;

    }
}