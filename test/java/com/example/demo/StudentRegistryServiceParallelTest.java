package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.helpers.StudentDataHelper.getAlphabeticString;
import static com.example.demo.helpers.StudentDataHelper.getNumber;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


class StudentRegistryServiceParallelTest {
    private StudentRegistryService studentRegistryService;
    private ApplicationEventPublisher applicationEventPublisher = Mockito.mock(ApplicationEventPublisher.class);



    @BeforeEach
    void beforeEach() {
        studentRegistryService = new StudentRegistryService(applicationEventPublisher);
        
    }
    @Test
    void addStudentParallel() {

        List<Callable<Student>> tasks = IntStream.range(0, 30)
                .mapToObj(i -> createAddStudentTask())
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            List<Future<Student>> futures = executorService.invokeAll(tasks);
            Awaitility.await().until(() -> futures.stream().allMatch(Future::isDone));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Student> size = studentRegistryService.getAllStudents()
                .stream()
                .toList();

        assertThat(size)
                .isNotEmpty()
                .hasSize(30);
    }

    private Callable<Student> createAddStudentTask() {
        return () -> {
            System.out.println("Test was started");
            String firstName = getAlphabeticString(3);
            String lastName = getAlphabeticString(7);
            int age = getNumber(2);
            return studentRegistryService.addStudent(firstName, lastName, age);
        };
    }
}
