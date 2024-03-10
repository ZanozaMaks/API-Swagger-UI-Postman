package ru.hogwarts.example.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.example.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<Long, Student> studentMap = new HashMap<>();

    private long counterStudentId = 0;

    @Override
    public Student addStudent (Student student) {
        long id = counterStudentId++;
        Student newStudent = new Student(id, student.getName(),student.getAge());
        studentMap.put(id, newStudent);
        return null;
    }

    @Override
    public void removeStudent(Long id) {
        studentMap.remove(id);

    }

    @Override
    public Student getStudent(Long id) {
        return studentMap.get(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentMap.get(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return studentMap.values().
                stream().
                filter(student ->
                        student.getAge() == age).
                toList();
    }
}
