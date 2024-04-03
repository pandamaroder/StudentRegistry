package com.example.demo.service;


import com.example.demo.StudentProperties;
import com.example.demo.listeners.StudentCreateEvent;
import com.example.demo.listeners.StudentDeleteEvent;
import com.example.demo.model.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentRegistryService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ConcurrentMap<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public StudentRegistryService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public Student addStudent(String firstName, String lastName, int age) {
        Student student = new Student(nextId.incrementAndGet(), firstName, lastName, age);
        students.put(student.getId(), student);
        StudentProperties con = new StudentProperties();
        con.getAge();
        applicationEventPublisher.publishEvent(new StudentCreateEvent(this, student));
        return student;
    }


    public void deleteStudent(Long id) {
        Student student = students.remove(id);
        applicationEventPublisher.publishEvent(new StudentDeleteEvent(this, student));
    }


    public List<Student> getAllStudents() {
        return List.copyOf(students.values());
    }


    public void clearAllStudents() {
        students.clear();
    }
}
