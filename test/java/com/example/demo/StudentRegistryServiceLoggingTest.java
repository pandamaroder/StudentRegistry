package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static com.example.demo.helpers.StudentDataHelper.getAlphabeticString;
import static com.example.demo.helpers.StudentDataHelper.getNumber;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class StudentRegistryServiceLoggingTest {

    @Autowired
    private StudentRegistryService studentRegistryService;

    @Test
    void validateAddStudentLoggingEvent(CapturedOutput output) throws InterruptedException {
        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
       studentRegistryService.addStudent(firstName, lastName, age);
       Thread.sleep(50);
        assertThat(output).contains("CREATE STUDENT EVENT");
    }

    @Test
    void validateDeleteStudentLoggingEvent(CapturedOutput output) throws InterruptedException {
        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
        Student student = studentRegistryService.addStudent(firstName, lastName, age);
        studentRegistryService.deleteStudent(student.getId());
        Thread.sleep(50);
        assertThat(output).contains("DELETE STUDENT EVENT");
    }
}
