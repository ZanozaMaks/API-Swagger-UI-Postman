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
        logger.info("Was invoked method for GetAllNameStartsWithA ");
        String firstLetterA = "A";
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith(firstLetterA))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAvgAgeStream() {
        logger.info("Was invoked method for getAvgAgeStream ");
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average().orElse(0);
    }

    public void studentsPrint () {
        List<Student> students = studentRepository.findAll();
        studentPrintThread(students.get(0));
        studentPrintThread(students.get(1));

        Thread thread1 = new Thread(() -> {
            studentPrintThread(students.get(2));
            studentPrintThread(students.get(3));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            studentPrintThread(students.get(4));
            studentPrintThread(students.get(5));
        });
        thread2.start();
    }

    public void studentsPrintSync () {
        List<Student> students = studentRepository.findAll();
        studentsPrintSync(students.get(0));
        studentsPrintSync(students.get(1));

        Thread thread1 = new Thread(() -> {
            studentsPrintSync(students.get(2));
            studentsPrintSync(students.get(3));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            studentsPrintSync(students.get(4));
            studentsPrintSync(students.get(5));
        });
        thread2.start();
    }

    private void studentPrintThread(Student student) {
        logger.info("Ttread: {}, Student: {}",Thread.currentThread(),student);
    }

    private synchronized void studentsPrintSync(Student student) {
        studentPrintThread(student);
    }
}

