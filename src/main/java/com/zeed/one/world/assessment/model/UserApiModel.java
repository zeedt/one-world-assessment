package com.zeed.one.world.assessment.model;

import com.zeed.one.world.assessment.enums.Role;
import com.zeed.one.world.assessment.enums.Status;

import java.time.LocalDateTime;

public class UserApiModel extends AbstractUserApiModel {

    private boolean verified;

    private LocalDateTime dateRegistered;

    private LocalDateTime dateVerified;

    private LocalDateTime dateActivated;

    private Status status;

    private boolean deleted;

    private Role role;

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalDateTime getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(LocalDateTime dateVerified) {
        this.dateVerified = dateVerified;
    }

    public LocalDateTime getDateActivated() {
        return dateActivated;
    }

    public void setDateActivated(LocalDateTime dateActivated) {
        this.dateActivated = dateActivated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
