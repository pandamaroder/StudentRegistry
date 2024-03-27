package com.example.demo.listeners;

import com.example.demo.model.Student;
import org.springframework.context.ApplicationEvent;

public class StudentCreateEvent extends ApplicationEvent {

    private final Student student;

    public StudentCreateEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
