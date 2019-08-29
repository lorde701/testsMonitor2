package ru.aplana.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aplana.entity.User;
import ru.aplana.request.AddUserRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    //user test1
    //id 123456789


    @Test
    public void test() {
        Long id = 1000001L;
        String name = "test2";

        userController.add(new AddUserRequest(id, name));
        User user = userController.getUserById(id).get();
        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getId(), id);

        userController.deleteById(user.getId());

        Assert.assertNull(userController.getUserById(user.getId()).orElse(null));
    }
}