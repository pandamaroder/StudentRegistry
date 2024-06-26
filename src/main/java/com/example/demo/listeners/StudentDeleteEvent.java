package com.example.demo.listeners;

import com.example.demo.model.Student;
import org.springframework.context.ApplicationEvent;

public class StudentDeleteEvent extends ApplicationEvent {


    public Student getStudent() {
        return student;
    }

    private final  Student student;

    public StudentDeleteEvent(Object source,  Student student) {
        super(source);

        this.student = student;
    }


}
