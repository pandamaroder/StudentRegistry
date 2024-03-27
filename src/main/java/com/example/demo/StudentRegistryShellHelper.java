package com.example.demo;


import com.example.demo.listeners.StudentEventListener;
import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;


@ShellComponent
public class StudentRegistryShellHelper {

    private final StudentRegistryService studentService;

    public StudentRegistryShellHelper(StudentRegistryService studentService) {
        this.studentService = studentService;
    }

    @ShellMethod
    public void addStudent(String firstName, String lastName, int age) {
        studentService.addStudent(firstName, lastName, age);
    }

    @ShellMethod
    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
    }

    @ShellMethod
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @ShellMethod
    public void clearAllStudents() {
        studentService.clearAllStudents();
    }
}
