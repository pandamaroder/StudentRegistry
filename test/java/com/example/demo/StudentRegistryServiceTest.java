package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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



class StudentRegistryServiceTest {


    @Test
    void addStudent() {

    }

}
