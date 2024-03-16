package ru.hogwarts.example.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.repository.FacultyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements  FacultyService {

    private final FacultyRepository facultyRepository;
    //private Map<Long, Faculty> facultyMap = new HashMap<>();

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {

        Faculty newFaculty = new Faculty(faculty.getName(), faculty.getColor());
        return facultyRepository.save(newFaculty);
    }

    @Override
    public void removeFaculty(Long id) {
        facultyRepository.delete(new Faculty());
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty existingFaculty = getFaculty(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public List<Faculty> getFacultyByColor(Integer color) {
        return facultyRepository.findAll().
                stream().
                filter(faculty ->
                        faculty.getColor().equals(color) ).
                toList();
    }
}

