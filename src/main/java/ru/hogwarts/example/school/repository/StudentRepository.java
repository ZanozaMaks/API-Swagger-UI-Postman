package ru.hogwarts.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.example.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAgeBetween(Integer min, Integer max);

    @Query(value = "SELECT COUNT(*) " + "FROM student",
            nativeQuery = true)
    int getCount();

    @Query(value = "SELECT AVG(age) " + "FROM student",
            nativeQuery = true)
    double getAvgAge();

    @Query (value = "SELECT * " + "FROM student"
            + "ORDER BY id DESC " + "LIMIT 5",
            nativeQuery = true)
    List<Student> getLastFiveStudentById();

}