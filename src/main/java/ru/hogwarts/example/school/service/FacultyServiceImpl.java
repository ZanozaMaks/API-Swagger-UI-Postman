package ru.hogwarts.example.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.example.school.model.Faculty;
import ru.hogwarts.example.school.model.Student;
import ru.hogwarts.example.school.repository.FacultyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements  FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);


    private final FacultyRepository facultyRepository;
    //private Map<Long, Faculty> facultyMap = new HashMap<>();

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for addFaculty ");
        Faculty newFaculty = new Faculty(faculty.getName(), faculty.getColor());
        return facultyRepository.save(newFaculty);
    }

    @Override
    public void removeFaculty(Long id) {
        logger.info("Was invoked method for removeFaculty ");
        facultyRepository.delete(new Faculty());
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for getFaculty ");
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        logger.info("Was invoked method for updateFaculty ");

        Faculty existingFaculty = getFaculty(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public List<Faculty> getFacultyByColor(Integer color) {
        logger.info("Was invoked method for getFacultyByColor ");
        return facultyRepository.findAll().
                stream().
                filter(faculty ->
                        faculty.getColor().equals(color) ).
                toList();
    }
}

