package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.model.UserApiModel;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.entities.User;
import com.zeed.one.world.assessment.model.UserUpdateApiModel;
import com.zeed.one.world.assessment.repository.UserRepository;
import com.zeed.one.world.assessment.services.UserService;
import com.zeed.one.world.assessment.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserApiModel createUser(UserCreationApiModel userCreationApiModel) {
        User user = GeneralUtil.convertCreateUserApiModelToEntity(userCreationApiModel);
        userRepository.save(user);
        return GeneralUtil.convertUserEntityToApiModel(user);
    }

    @Override
    public UserApiModel updateUser(UserUpdateApiModel userUpdateApiModel, String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with ID %s not found", id));
        User user = optionalUser.get();

        if (user.isDeleted())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, String.format("User with ID %s already deactivated", id));

        user.setTitle(StringUtils.isEmpty(userUpdateApiModel.getTitle()) ? user.getTitle() : userUpdateApiModel.getTitle());
        user.setFirstName(StringUtils.isEmpty(userUpdateApiModel.getFirstName()) ? user.getFirstName() : userUpdateApiModel.getFirstName());
        user.setLastName(StringUtils.isEmpty(userUpdateApiModel.getLastName()) ? user.getLastName() : userUpdateApiModel.getLastName());
        user.setMobileNumber(StringUtils.isEmpty(userUpdateApiModel.getMobileNumber()) ? user.getMobileNumber() : userUpdateApiModel.getMobileNumber());
        user.setEmail(StringUtils.isEmpty(userUpdateApiModel.getEmail()) ? user.getEmail() : userUpdateApiModel.getEmail());
        userRepository.save(user);
        return GeneralUtil.convertUserEntityToApiModel(user);
    }

    @Override
    public Page<UserApiModel> getUser(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void deleteUser(String id) {
        if (userRepository.updateDeleteStatus(new Date(), id) == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to locate record or User already deactivated");

    }
}
