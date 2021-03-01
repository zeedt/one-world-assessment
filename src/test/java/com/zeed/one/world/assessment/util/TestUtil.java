package com.zeed.one.world.assessment.util;

import com.zeed.one.world.assessment.entities.User;
import com.zeed.one.world.assessment.enums.Role;
import com.zeed.one.world.assessment.enums.Status;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.model.UserSearchApiModel;
import com.zeed.one.world.assessment.model.UserUpdateApiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestUtil {

    public static UserCreationApiModel getMockedUserCreationApiModel() {
        UserCreationApiModel userCreationApiModel = new UserCreationApiModel();
        userCreationApiModel.setPassword("password");
        userCreationApiModel.setRole(Role.USER);
        userCreationApiModel.setEmail("ola@email.com");
        userCreationApiModel.setFirstName("Olamide");
        userCreationApiModel.setLastName("Ishola");
        userCreationApiModel.setMobileNumber("23400000000");
        userCreationApiModel.setTitle("Mr.");

        return userCreationApiModel;
    }

    public static UserUpdateApiModel getMockedUserUpdateApiModel() {
        UserUpdateApiModel userUpdateApiModel = new UserUpdateApiModel();
        userUpdateApiModel.setEmail("shola@email.com");
        userUpdateApiModel.setFirstName("Shola");
        userUpdateApiModel.setLastName("John");
        userUpdateApiModel.setTitle("Prof.");

        return userUpdateApiModel;
    }

    public static Optional<User> getMockedResponseForNotPresentUser() {
        return Optional.empty();
    }

    public static Optional<User> getMockedResponseForUserPresent() {
        User user = new User();
        user.setStatus(Status.VERIFIED);
        user.setDateVerified(LocalDateTime.now());
        user.setVerified(false);
        user.setEmail("ishow@email.com");
        user.setDeleted(false);
        user.setApprovalCode("952ED1");
        user.setLastName("ishow");
        user.setFirstName("Olakunle");
        user.setTitle("Mr.");
        user.setDateRegistered(LocalDateTime.now());
        user.setMobileNumber("234800000000");
        user.setRole(Role.USER);
        user.setDateVerified(LocalDateTime.now());
        user.setId("ca2a3c19-2113-4d48-a7e3-27b66b95db5b");

        return Optional.of(user);
    }

    public static UserSearchApiModel getMockedUserSearchApiRequest() {
        UserSearchApiModel userSearchApiModel = new UserSearchApiModel();
        userSearchApiModel.setVerified(true);
        userSearchApiModel.setPageNo(0);
        userSearchApiModel.setPageSize(20);

        return userSearchApiModel;
    }


    public static Page<User> getMockedPagedUser() {
        List<User> users = new ArrayList<>();
        User user1 = getMockedResponseForUserPresent().get();
        User user2 = getMockedResponseForUserPresent().get();
        user2.setTitle("Prof.");
        user2.setEmail("mockedemail@yahoo.com");
        user2.setId("1ed1a2e9-98fb-4994-a22a-dfb1ee1149d7");


        return new PageImpl<>(Arrays.asList(new User[]{user1, user2}),
                PageRequest.of(0, 20), 40);
    }
}
