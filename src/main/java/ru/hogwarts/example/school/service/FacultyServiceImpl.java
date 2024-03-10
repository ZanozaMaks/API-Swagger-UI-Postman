package ru.hogwarts.example.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements  FacultyService {

    private Map<Long, Faculty> facultyMap = new HashMap<>();

    private long counterFacultyId = 0;
    @Override
    public Faculty addFaculty (Faculty faculty) {
        long id = counterFacultyId;
        counterFacultyId++;
        Faculty newFaculty = new Faculty(id,faculty.getName(), faculty.getColor());
        facultyMap.put(id,newFaculty);
        return newFaculty;
    }

    @Override
    public void removeFaculty(Long id) {
        facultyMap.remove(id);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyMap.get(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public List<Faculty> getFacultyByColor(Integer color) {
        return facultyMap.values().
                stream().
                filter(faculty ->
                        faculty.getColor().equals(color) ).
                toList();
    }
}

