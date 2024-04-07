package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.helpers.StudentDataHelper.getAlphabeticString;
import static com.example.demo.helpers.StudentDataHelper.getNumber;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentRegistryServiceTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private StudentRegistryService studentRegistryService;


    @Test
    void addStudentParallel() {

        List<Callable<Student>> tasks = IntStream.range(0, 3)
                .mapToObj(i -> createAddStudentTask(studentRegistryService))
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //TODO make x2 task invocations
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Callable<Student> createAddStudentTask(StudentRegistryService studentRegistryService) {
        return () -> {
            String firstName = getAlphabeticString(3);
            String lastName = getAlphabeticString(7);
            int age = getNumber(2);
            return studentRegistryService.addStudent(firstName, lastName, age);
        };
    }
}
