package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class Student {

        private Long id;
        private String firstName;
        private String lastName;
        private int age;


}
