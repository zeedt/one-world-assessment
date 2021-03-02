package com.zeed.one.world.assessment.services;

import com.zeed.one.world.assessment.model.UserApiModel;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.model.UserSearchApiModel;
import com.zeed.one.world.assessment.model.UserSearchResponseModel;
import com.zeed.one.world.assessment.model.UserUpdateApiModel;

public interface UserService {

    UserApiModel createUser(UserCreationApiModel userCreationApiModel);

    UserApiModel updateUser(UserUpdateApiModel userUpdateApiModel, String id);

    UserSearchResponseModel getUser(UserSearchApiModel searchApiModel);

    void deleteUser(String id);

    String verifyUser(String id, String approvalCode);

}
