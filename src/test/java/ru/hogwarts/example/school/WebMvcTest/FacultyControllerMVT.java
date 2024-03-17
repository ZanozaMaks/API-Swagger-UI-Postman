package ru.hogwarts.example.school.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.example.school.controller.AvatarController;
import ru.hogwarts.example.school.controller.FacultyController;
import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.repository.AvatarRepository;
import ru.hogwarts.example.school.repository.FacultyRepository;
import ru.hogwarts.example.school.repository.StudentRepository;
import ru.hogwarts.example.school.service.AvatarService;
import ru.hogwarts.example.school.service.FacultyServiceImpl;
import ru.hogwarts.example.school.service.StudentServiceImpl;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FacultyControllerMVT {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private AvatarController avatarController;
    @SpyBean
    private FacultyServiceImpl facultyService;
    @SpyBean
    private StudentServiceImpl studentService;
    @SpyBean
    private AvatarService avatarService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFaculty() throws Exception {
        final String name = "слизерин";
        final String color = "зеленый";
        final long id = 1L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, color);
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getAllFacultyPerOne() throws Exception {
        final String name = "Gryffindor";
        final String color = "red";
        final long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        final String name1 = "Slytherin";
        final String color1 = "green";
        final long id1 = 2L;
        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColor(color1);
        List<Faculty> arrayFaculty = new ArrayList<>(List.of(faculty, faculty1));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty1);
        when(facultyRepository.findAll()).thenReturn(arrayFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void FacultyPutTest() throws Exception {
        final String name = "test1";
        final String color = "test1";
        final String name1 = "test2";
        final String color1 = "test2";
        final Long id = 1L;

        JSONObject objectFaculty2 = new JSONObject();
        objectFaculty2.put("id", id);
        objectFaculty2.put("name", name1);
        objectFaculty2.put("color", color1);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.put("/faculty/put/1")
                        .content(objectFaculty2.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));
    }
    @Test
    public void FacultyDeleteTest() throws Exception {
        final String name = "Klim";
        final String color = "red";
        final Long id = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.delete("/faculty/remove/1")).
                andExpect(status().isOk())
                .andReturn();
    }
}
