package ru.aplana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aplana.entity.Project;
import ru.aplana.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private
    ProjectRepository projectRepository;

    public List<Project> findAll() {
        List<Project> users = new ArrayList<>();
        projectRepository.findAll().forEach(users::add);
        return users;
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name).get();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).get();
    }
}
