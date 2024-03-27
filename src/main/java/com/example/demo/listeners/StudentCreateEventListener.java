package com.example.demo.listeners;

import com.example.demo.model.Student;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentCreateEventListener {

    @EventListener
    public void handleStudentCreateEvent(StudentCreateEvent event) {
        Student student = event.getStudent();
        // Действия, выполняемые при возникновении события создания студента
        System.out.println("New student created: " + student.toString());
    }

}
