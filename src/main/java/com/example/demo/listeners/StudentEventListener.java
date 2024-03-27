package com.example.demo.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void handleStudentCreatedEvent(StudentCreateEvent event) {
        System.out.println("Student created: " + event.getStudent());
    }


}