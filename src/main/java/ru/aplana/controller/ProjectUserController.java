package ru.aplana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.aplana.entity.Project;
import ru.aplana.entity.User;
import ru.aplana.repository.ProjectRepository;
import ru.aplana.repository.UserRepository;
import ru.aplana.request.AddUserToProject;

@RestController
@RequestMapping("/api")
public class ProjectUserController {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/addUserToProject", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUserToProject(@RequestBody AddUserToProject request) {
        if (userRepository.findById(request.getUserId()).isPresent() &&
                projectRepository.findById(request.getProjectId()).isPresent()) {
            User user = userRepository.findById(request.getUserId()).get();
            Project project = projectRepository.findById(request.getProjectId()).get();

//            user.getProjects().add(project);
            project.getUsers().add(user);
//            userRepository.save(user);
            projectRepository.save(project);
        }
    }
}
