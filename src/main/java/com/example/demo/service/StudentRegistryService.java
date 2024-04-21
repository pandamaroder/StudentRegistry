package com.example.demo.service;


import com.example.demo.StudentProperties;
import com.example.demo.listeners.StudentCreateEvent;
import com.example.demo.listeners.StudentDeleteEvent;
import com.example.demo.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentRegistryService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ConcurrentMap<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Student addStudent(String firstName, String lastName, int age) {
        Student student = new Student(nextId.incrementAndGet(), firstName, lastName, age);
        students.put(student.getId(), student);
        applicationEventPublisher.publishEvent(new StudentCreateEvent(this, student));
        log.info("CREATE STUDENT EVENT");
        return student;
    }

    public Student deleteStudent(Long id) {
        Student student = students.remove(id);
        applicationEventPublisher.publishEvent(new StudentDeleteEvent(this, student));
        log.info("DELETE STUDENT EVENT");
        return student;
    }


    public List<Student> getAllStudents() {
        return List.copyOf(students.values());
    }


    public boolean clearAllStudents() {
        students.clear();
        return students.isEmpty();
    }
}
