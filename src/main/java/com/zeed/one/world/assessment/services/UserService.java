package com.zeed.one.world.assessment.services;

import com.zeed.one.world.assessment.model.*;
import org.springframework.data.domain.Page;

public interface UserService {

    UserApiModel createUser(UserCreationApiModel userCreationApiModel);

    UserApiModel updateUser(UserUpdateApiModel userUpdateApiModel, String id);

    UserSearchResponseModel getUser(UserSearchApiModel searchApiModel);

    void deleteUser(String id);

}
