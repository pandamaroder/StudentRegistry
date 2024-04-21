package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.helpers.StudentDataHelper.getAlphabeticString;
import static com.example.demo.helpers.StudentDataHelper.getNumber;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(OutputCaptureExtension.class)
class StudentRegistryServiceTest {

    private StudentRegistryService studentRegistryService;
    private ApplicationEventPublisher applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);


    @BeforeEach
    void beforeEach() {

        studentRegistryService = new StudentRegistryService(applicationEventPublisher);

    }

    @Test
    void validateAddStudent() {

        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
       studentRegistryService.addStudent(firstName, lastName, age);
        List<Student> allStudents = studentRegistryService.getAllStudents();
        assertThat(allStudents)
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void validateNewStudentFirstNameParameters() {
        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
        studentRegistryService.addStudent(firstName, lastName, age);
        List<Student> allStudents = studentRegistryService.getAllStudents();
        assertThat(allStudents)
                .hasSize(1)
                .first()
                .hasFieldOrProperty("firstName")
                .hasFieldOrPropertyWithValue("firstName", firstName);
    }

    @Test
    void validateNewStudentLastNameParameters() {
        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
        studentRegistryService.addStudent(firstName, lastName, age);
        List<Student> allStudents = studentRegistryService.getAllStudents();
        assertThat(allStudents)
                .hasSize(1)
                .first()
                .hasFieldOrProperty("lastName")
                .hasFieldOrPropertyWithValue("lastName", lastName);
    }

    @Test
    void validateNewStudentAgeParameters() {
        String firstName = getAlphabeticString(3);
        String lastName = getAlphabeticString(7);
        int age = getNumber(2);
        studentRegistryService.addStudent(firstName, lastName, age);
        List<Student> allStudents = studentRegistryService.getAllStudents();
        assertThat(allStudents)
                .hasSize(1)
                .first()
                .hasFieldOrProperty("age")
                .hasFieldOrPropertyWithValue("age", age);
    }
}
