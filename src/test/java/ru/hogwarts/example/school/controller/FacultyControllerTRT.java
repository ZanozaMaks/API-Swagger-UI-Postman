package ru.hogwarts.example.school.controller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.repository.FacultyRepository;
import ru.hogwarts.example.school.repository.StudentRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FacultyRestTemplateApplicationTest {
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
    public void contextLoad() throws Exception {
        Assertions.assertThat(avatarController).isNotNull();
    }

    @Test
    public void testGetAvatars() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/avatars", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "/get/", String.class))
                .isNotNull();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty("Griffindor", "red");
        Faculty post = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        Optional resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/get/" + post.getId(), Optional.class);
        assertFalse(resp.isEmpty());
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty("Slitherin", "red");
        Faculty facultyPut = new Faculty("Fill", "red");
        Faculty post = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        facultyPut.setId(post.getId());
        this.testRestTemplate.put("http://localhost:" + port + "/faculty/put/" + post.getId(), facultyPut, Faculty.class);
        Faculty resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/get/" + post.getId(), Faculty.class);
        assertTrue(facultyPut.equals(resp));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty("Jhony", "yellow");
        Faculty post = this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        Optional resp = this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/get/" + post.getId(), Optional.class);
        this.testRestTemplate.delete("http://localhost:" + port + "/faculty/remove/" + post.getId());
        Optional resp1 = this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/get/" + post.getId(), Optional.class);
        if (resp1 == null) {
            assertFalse(resp.equals(resp1));
        }

    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "/students-by-faculty-id", String.class))
                .isNotNull();
    }
}