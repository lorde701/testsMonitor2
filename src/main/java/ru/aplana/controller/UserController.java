package ru.aplana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.aplana.entity.User;
import ru.aplana.repository.UserRepository;
import ru.aplana.request.AddUserRequest;
import ru.aplana.service.UserService;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }




}
