package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentRegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;


@ShellComponent
public class StudentRegistryShellHelper {
     private final StudentRegistryService studentService;

    public StudentRegistryShellHelper(StudentRegistryService studentService) {
        this.studentService = studentService;
    }

    @ShellMethod("Добавить студента")
    public void addStudent(String firstName, String lastName, int age) {
        studentService.addStudent(firstName, lastName, age);
    }

    @ShellMethod("Удалить студента")
    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
    }

    @ShellMethod("Получить список студентов")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @ShellMethod("Очистить список студентов")
    public void clearAllStudents() {
        studentService.clearAllStudents();
    }

    @ShellMethodAvailability({"clear-all-students", "get-all-students", "delete-student"})
    public Availability availabilityCheck() {
        return !studentService.getAllStudents().isEmpty()
                ? Availability.available() : Availability.unavailable("Мапа студентов пустая");
    }
}
