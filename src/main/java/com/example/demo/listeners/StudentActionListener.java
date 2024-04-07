package com.example.demo.listeners;

import com.example.demo.StudentProperties;
import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class StudentActionListener {

    private final StudentRegistryService studentRegistryService;
    private final StudentProperties properties;

    private Boolean isServiceEnabled() {
        return properties.getEnabled();
    }

    @EventListener
    public void handleStudentCreateEvent(StudentCreateEvent event) {
        Student student = event.getStudent();
        System.out.println("CREATION EVENT: New student created: " + student.toString());
        log.info("CREATION EVENT: New student created: " + student.toString());
    }

    @EventListener
    public void handleStudentDeleteEvent(StudentDeleteEvent event) {
        Long id = event.getStudent().getId();
        System.out.println("DELETION EVENT: Student was deleted");
   }

    @EventListener
    public void handleApplicationReadyEvent(ApplicationStartedEvent event) {
        if (this.isServiceEnabled()) {
            StudentProperties studentProperties = new StudentProperties();
            studentRegistryService.addStudent(studentProperties.getFirstName(),
                    studentProperties.getLastName(), studentProperties.getAge());
            System.out.println("APP READY EVENT: APPLICATION WAS STARTED ");
        }
    }
}

