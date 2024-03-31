package com.example.demo.service;


import com.example.demo.StudentProperties;
import com.example.demo.listeners.StudentCreateEvent;
import com.example.demo.listeners.StudentDeleteEvent;
import com.example.demo.model.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentRegistryService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Map<Long, Student> students = new HashMap<>();
    private long nextId = 1;

    public StudentRegistryService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public Student addStudent(String firstName, String lastName, int age) {
        Student student = new Student(nextId++, firstName, lastName, age);
        students.put(student.getId(), student);
        StudentProperties con = new StudentProperties();
        con.getAge();
        applicationEventPublisher.publishEvent(new StudentCreateEvent(this, student));

        return student;
    }


    public void deleteStudent(Long id) {
        students.remove(id);
        applicationEventPublisher.publishEvent(new StudentDeleteEvent(this, id));

    }


    public List<Student> getAllStudents() {
        return List.copyOf(students.values());

    }


    public void clearAllStudents() {
        students.clear();
    }
}
