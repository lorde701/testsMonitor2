package ru.aplana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.aplana.entity.Project;
import ru.aplana.entity.User;
import ru.aplana.repository.ProjectRepository;
import ru.aplana.request.AddProjectRequest;
import ru.aplana.request.UserProjectRequest;
import ru.aplana.service.ProjectService;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserController userController;


    @RequestMapping(value = "/addProject", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Project add(@RequestBody AddProjectRequest request) {
        Project project = new Project(request.getProjectName());
        return projectRepository.save(project);
    }

    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    public List<Project> getAll() {
        return projectService.findAll();
    }

    @RequestMapping(value = "/getProjectById/{id}", method = RequestMethod.GET)
    public Project getById(@PathVariable Long id) {
        try {
            return projectService.findById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUserToProject(@RequestBody UserProjectRequest request) throws Exception {
        updateUser(request, (users, user) -> {
            Set<User> result;
            if (users == null) {
                result = new HashSet<>();
            } else {
                result = new HashSet<>(users);
            }
            result.add(user);
            return result;
        });
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void removeUserFromProject(@RequestBody UserProjectRequest request) throws Exception {
        updateUser(request, (users, user) -> {
            Set<User> result = new HashSet<>(users);
            result.remove(user);
            return result;
        });
    }


    private void updateUser(UserProjectRequest request, BiFunction<Set<User>, User, Set<User>> biFunction) throws Exception {
        User user = userController.getUserById(request.getUserId())
                .orElseThrow(() -> new Exception(String.format("В таблице User не был найден user с id <%s>"
                        , request.getUserId())));
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new Exception(String.format("В таблице Project не был найден project с id <%s>"
                        , request.getProjectId())));

        Set<User> users = project.getUsers();
        users = biFunction.apply(users, user);
        project.setUsers(users);
        projectRepository.save(project);
    }

}



