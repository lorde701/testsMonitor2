package ru.aplana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.aplana.entity.Project;
import ru.aplana.repository.ProjectRepository;
import ru.aplana.request.AddProjectRequest;
import ru.aplana.request.AddUserToProject;
import ru.aplana.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private
    ProjectRepository projectRepository;

    @Autowired
    private
    ProjectService projectService;

    @RequestMapping(value = "/addProject", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Project add(@RequestBody AddProjectRequest request) {
        Project project = new Project(request.getProjectName());
        return projectRepository.save(project);
    }

    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    public List<Project> getAll() {
        return projectService.findAll();
    }

//    @RequestMapping(value = "/addUserToProject", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public void addUserToProject(@RequestBody AddUserToProject request) {
//        Project project = projectRepository.findById(request.getProjectId()).get();
//        if (project != null) {
//            project.getUsers().add()
//        }
//
//    }
}
