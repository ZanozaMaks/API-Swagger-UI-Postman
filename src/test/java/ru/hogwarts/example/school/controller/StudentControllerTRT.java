package ru.hogwarts.example.school.controller;


import org.junit.jupiter.api.Test;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.repository.FacultyRepository;
import ru.hogwarts.example.school.repository.StudentRepository;
import java.util.Optional;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StudentRestTemplateApplicationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private AvatarController avatarController;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate testRestTemplate;



    @Test
    public void testGetStudent() throws Exception {
        Optional resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/students/get/18", Optional.class);
        assertFalse(resp.isEmpty());
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student("Alexey");
        student.setId(6L);
        Student post = this.testRestTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class);
        Optional resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/students/get/"+post.getId(), Optional.class);
        assertFalse(resp.isEmpty());
    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student("Alexey");
        student.setId(7L);
        Student studentPut = new Student("Вади");
        Student post = this.testRestTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class);
        studentPut.setId(post.getId());
        this.testRestTemplate.put("http://localhost:" + port + "/students/" + post.getId(), studentPut, Student.class);
        Student resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/students/get/" + post.getId(), Student.class);
        assertTrue(studentPut.equals(resp));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student("Михвил");
        student.setId(8L);
        Student post = this.testRestTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class);
        Optional resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/students/get/" + post.getId(), Optional.class);
        this.testRestTemplate.delete("http://localhost:" + port + "/students/" + post.getId());
        Optional resp1 = this.testRestTemplate.getForObject("http://localhost:" + port + "/students/get/" + post.getId(), Optional.class);
        if (resp1 == null) {
            assertFalse(resp.equals(resp1));
        }
    }

}