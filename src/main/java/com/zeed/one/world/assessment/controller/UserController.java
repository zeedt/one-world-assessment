package com.zeed.one.world.assessment.controller;


import com.zeed.one.world.assessment.model.UserApiModel;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.model.UserUpdateApiModel;
import com.zeed.one.world.assessment.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

    @PostMapping
    public UserApiModel createUser(@RequestBody @Validated UserCreationApiModel userCreationApiModel) {
        try {
            return userService.createUser(userCreationApiModel);
        } catch (Exception e) {
            LOGGER.error("Error occurred while creating user due to ", e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to create user at this time. Please try again");
        }
    }

    @PutMapping("/{id}")
    public UserApiModel updateUser(@RequestBody @Validated UserUpdateApiModel userUpdateApiModel, @PathVariable String id) {
        return userService.updateUser(userUpdateApiModel, id);
    }

}
