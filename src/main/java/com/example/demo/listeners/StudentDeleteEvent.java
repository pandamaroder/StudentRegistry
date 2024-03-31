package com.example.demo.listeners;

import com.example.demo.model.Student;
import org.springframework.context.ApplicationEvent;

public class StudentDeleteEvent extends ApplicationEvent {

    public Long getId() {
        return id;
    }

    private final Long id;

    public StudentDeleteEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }


}
