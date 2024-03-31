package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "student.default")
public class StudentProperties {

    private int age;
    private String firstName;
    private String lastName;
    private Boolean enabled;

}
