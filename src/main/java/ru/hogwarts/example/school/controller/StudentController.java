package ru.hogwarts.example.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping ("{id}")
    public Student getStudent (@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping ("{id}")
    public Student updateStudent (@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping ("{id}")
    public void removeStudent (@PathVariable Long id) {
        studentService.removeStudent(id);
    }

    @GetMapping
    public Collection<Student> getStudentByAge(@RequestParam int age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping ("ageBetween")
    public List<Student> getWhenAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.getWhenAgeBetween(min, max);
    }

    @GetMapping("/student_count")
    public int getStudCount() {
        return studentService.getCount();
    }

    @GetMapping("/student_avg_age")
    public double getAvgAge() {
        return studentService.avgAge();
    }
    @GetMapping("/student_last_five_stud")
    public List<Student> getLastFiveStud() {
        return studentService.LastFiveStud();
    }
}
