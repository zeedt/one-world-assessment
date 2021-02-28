package com.zeed.one.world.assessment.util;


import com.zeed.one.world.assessment.model.UserApiModel;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.entities.User;
import com.zeed.one.world.assessment.enums.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class GeneralUtil {

    private static final String APPROVAL_CODE_CHARACTERS = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static User convertCreateUserApiModelToEntity(UserCreationApiModel userCreationApiModel) {
        User user = new User();
        user.setFirstName(userCreationApiModel.getFirstName());
        user.setLastName(userCreationApiModel.getLastName());
        user.setEmail(userCreationApiModel.getEmail());
        user.setMobileNumber(userCreationApiModel.getMobileNumber());
        user.setTitle(userCreationApiModel.getTitle());
        user.setRole(userCreationApiModel.getRole());
        user.setPassword(PASSWORD_ENCODER.encode(userCreationApiModel.getPassword()));
        user.setDateRegistered(LocalDateTime.now());
        user.setStatus(Status.REGISTERED);
        user.setApprovalCode(generateApprovalCode());
        user.setVerified(false);
        user.setDeleted(false);

        return user;
    }

    public static UserApiModel convertUserEntityToApiModel(User user) {
        UserApiModel userApiModel = new UserApiModel();
        userApiModel.setDateActivated(user.getDateDeactivated());
        userApiModel.setDateRegistered(user.getDateRegistered());
        userApiModel.setDeleted(user.isDeleted());
        userApiModel.setEmail(user.getEmail());
        userApiModel.setFirstName(user.getFirstName());
        userApiModel.setLastName(user.getLastName());
        userApiModel.setMobileNumber(user.getMobileNumber());
        userApiModel.setId(user.getId());
        userApiModel.setRole(user.getRole());
        userApiModel.setStatus(user.getStatus());
        userApiModel.setDateVerified(user.getDateVerified());
        userApiModel.setTitle(user.getTitle());
        userApiModel.setVerified(user.isVerified());

        return userApiModel;
    }

    public static String generateApprovalCode() {
        Random random = new Random();
        char[] password = new char[6];
        for (int i = 0; i < 6; i++) {
            password[i] = APPROVAL_CODE_CHARACTERS.charAt(random.nextInt(APPROVAL_CODE_CHARACTERS.length()));
        }
        return String.valueOf(password);
    }

}
