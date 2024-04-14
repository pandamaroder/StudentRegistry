package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static com.example.demo.helpers.StudentDataHelper.getAlphabeticString;
import static com.example.demo.helpers.StudentDataHelper.getNumber;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(OutputCaptureExtension.class)
class StudentRegistryServiceLoggingTest {

    private StudentRegistryService studentRegistryService;
    private ApplicationEventPublisher applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);


    @BeforeEach
    void beforeEach() {
        studentRegistryService = new StudentRegistryService(applicationEventPublisher);

    }
    @Test
    void validateAddStudentLoggingEvent(CapturedOutput output) {
        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
       studentRegistryService.addStudent(firstName, lastName, age);
        assertThat(output).contains("CREATE STUDENT EVENT");


    }

}
