package com.zeed.one.world.assessment.services;

import com.zeed.one.world.assessment.model.UserApiModel;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.model.UserUpdateApiModel;
import org.springframework.data.domain.Page;

public interface UserService {

    UserApiModel createUser(UserCreationApiModel userCreationApiModel);

    UserApiModel updateUser(UserUpdateApiModel userUpdateApiModel, String id);

    Page<UserApiModel> getUser(int pageNo, int pageSize);

    void deleteUser(String id);

}
