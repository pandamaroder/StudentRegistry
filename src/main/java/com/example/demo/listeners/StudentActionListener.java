package com.example.demo.listeners;

import com.example.demo.StudentProperties;
import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
    }

    @EventListener
    public void handleStudentDeleteEvent(StudentDeleteEvent event) {
        Long id = event.getId();
        // Действия, выполняемые при возникновении события создания студента
        System.out.println("DELETION EVENT: Student was deleted");
    }

    @EventListener(
            value = ApplicationReadyEvent.class,
            condition = "@studentActionListener.isServiceEnabled()")
    public void handleApplicationReadyEvent() {
            StudentProperties studentProperties = new StudentProperties();
            studentRegistryService.addStudent(studentProperties.getFirstName(),
                    studentProperties.getLastName(), studentProperties.getAge());
            System.out.println("New student created: " +  studentRegistryService.getAllStudents());
    }
}

