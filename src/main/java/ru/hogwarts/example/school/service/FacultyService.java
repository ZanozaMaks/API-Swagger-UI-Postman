package ru.hogwarts.example.school.service;

import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.model.Student;

import java.util.List;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);
    void removeFaculty(Long id);
    Faculty getFaculty(Long id);
    Faculty updateFaculty(Long id, Faculty faculty);

    List<Faculty> getFacultyByColor(Integer color);

}
