package ru.hogwarts.example.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.service.FacultyService;

import java.util.Collection;

public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public void removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
    }

    @GetMapping
    public Collection<Faculty> getFacultyByColor(@RequestParam Integer color) {
        return facultyService.getFacultyByColor(color);
    }
}
