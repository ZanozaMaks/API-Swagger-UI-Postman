package ru.hogwarts.example.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.repository.StudentRepository;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    //private Map<Long, Student> studentMap = new HashMap<>();

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        Student newStudent = new Student(student.getName());
        return studentRepository.save(newStudent);
    }


    @Override
    public Student removeStudent(Long id) {
        Student studentForDelete = getStudent(id);
        studentRepository.deleteById(id);
        return studentForDelete;

    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = getStudent(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return studentRepository.findAll().
                stream().
                filter(student ->
                        student.getAge() == age).
                toList();
    }

    public List<Student> getWhenAgeBetween(Integer min, Integer max) {
        return studentRepository.findAllByAgeBetween(min,max);
    }
}
