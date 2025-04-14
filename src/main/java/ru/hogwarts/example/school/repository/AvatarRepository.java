package ru.hogwarts.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.example.school.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {



}
