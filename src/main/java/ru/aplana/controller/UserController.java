package ru.aplana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.aplana.entity.User;
import ru.aplana.repository.UserRepository;
import ru.aplana.request.AddUserRequest;
import ru.aplana.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private
    UserRepository userRepository;

    @Autowired
    private
    UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User add(@RequestBody AddUserRequest request) {
        return userRepository.save(new User(request.getId(), request.getName()));
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getUsers();
    }

}
