package com.zeed.one.world.assessment.controller;


import com.zeed.one.world.assessment.model.*;
import com.zeed.one.world.assessment.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

    @PostMapping("/user")
    public UserApiModel createUser(@RequestBody @Validated UserCreationApiModel userCreationApiModel) {
        try {
            return userService.createUser(userCreationApiModel);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error occurred while creating user due to ", e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create user at this time. Please try again");
        }
    }

    @PutMapping("/user/{id}")
    public UserApiModel updateUser(@RequestBody @Validated UserUpdateApiModel userUpdateApiModel, @PathVariable String id) {
        return userService.updateUser(userUpdateApiModel, id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users")
    public UserSearchResponseModel getUsers(UserSearchApiModel userSearchApiModel) {
        return userService.getUser(userSearchApiModel);
    }

    @GetMapping("/user/verify/{id}/{approvalCode}")
    public String verifyUser(@PathVariable String id, @PathVariable String approvalCode) {
        return userService.verifyUser(id, approvalCode);
    }
}
