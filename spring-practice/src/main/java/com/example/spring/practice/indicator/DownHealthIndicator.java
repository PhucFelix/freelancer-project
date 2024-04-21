package com.example.spring.practice.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class DownHealthIndicator {
    public Health health() {
        return Health.status(Status.DOWN).build();
    }
}
