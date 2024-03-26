package ru.hogwarts.example.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.repository.StudentRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    private final StudentRepository studentRepository;
    //private Map<Long, Student> studentMap = new HashMap<>();

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method for addStudent ");

        Student newStudent = new Student(student.getName());
        return studentRepository.save(newStudent);
    }


    @Override
    public Student removeStudent(Long id) {
        logger.info("Was invoked method for removeStudent ");

        Student studentForDelete = getStudent(id);
        studentRepository.deleteById(id);
        return studentForDelete;

    }

    @Override
    public Student getStudent(Long id) {
        logger.info("Was invoked method for getStudent ");

        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        logger.info("Was invoked method for updateStudent ");

        Student existingStudent = getStudent(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        logger.info("Was invoked method for getStudentByAge ");

        return studentRepository.findAll().
                stream().
                filter(student ->
                        student.getAge() == age).
                toList();
    }

    public List<Student> getWhenAgeBetween(Integer min, Integer max) {
        logger.info("Was invoked method for getWhenAgeBetween ");

        return studentRepository.findAllByAgeBetween(min, max);
    }


    public List<String> GetAllNameStartsWithA() {
        String firstLetterA = "A";
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith(firstLetterA))
                .sorted()
                .collect(Collectors.toList());
    }

}

