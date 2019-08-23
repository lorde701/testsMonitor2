package ru.aplana.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aplana.entity.Project;
import ru.aplana.entity.User;
import ru.aplana.request.AddProjectRequest;
import ru.aplana.request.AddUserRequest;
import ru.aplana.request.UserProjectRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;

    @Autowired
    private UserController userController;

    //name - testProject2
    //id - 2

    @Test
    public void check() {
        String projectName = "project1";

        Project project = projectController.add(AddProjectRequest.builder().projectName(projectName).build());
        Assert.assertEquals(project.getName(), projectName);
        Project projectFromService = projectController.getById(project.getId());
        Assert.assertEquals(projectFromService.getName(), projectName);
        projectController.deleteById(project.getId());
        Assert.assertNull(projectController.getById(project.getId()));
    }

    @Test
    public void addProjectName() {
        String projectName = "testProject2";
        Project project = projectController.add(AddProjectRequest.builder().projectName(projectName).build());
        Assert.assertEquals(project.getName(), projectName);
    }

    @Test
    public void addLink() throws Exception {
        Project project = projectController.add(AddProjectRequest.builder().projectName("zxc1").build());
        User user = userController.add(AddUserRequest.builder().id(111L).name("qwe789").build());


        projectController.addUserToProject(UserProjectRequest.builder().userId(user.getId()).projectId(project.getId()).build());
        projectController.removeUserFromProject(UserProjectRequest.builder().userId(user.getId()).projectId(project.getId()).build());
        projectController.getById(project.getId());
    }
}