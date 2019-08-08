package ru.aplana.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aplana.entity.Project;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
