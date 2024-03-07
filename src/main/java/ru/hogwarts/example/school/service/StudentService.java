package ru.hogwarts.example.school.service;

import ru.hogwarts.example.school.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);
    void removeStudent(Long id);
    Student getStudent(Long id);
    Student updateStudent(Long id, Student student);

    List<Student> getStudentByAge(int age);
}
