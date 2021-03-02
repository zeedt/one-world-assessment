package com.zeed.one.world.assessment.model;

import com.zeed.one.world.assessment.enums.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserCreationApiModel extends AbstractUserApiModel {

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
