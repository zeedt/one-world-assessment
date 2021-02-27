package com.zeed.one.world.assessment.model;

import com.zeed.one.world.assessment.enums.Role;
import com.zeed.one.world.assessment.enums.Status;
import java.util.Date;

public class UserApiModel extends AbstractUserApiModel {

    private boolean verified;

    private Date dateRegistered;

    private Date dateVerified;

    private Date dateActivated;

    private Status status;

    private boolean deleted;

    private Role role;

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(Date dateVerified) {
        this.dateVerified = dateVerified;
    }

    public Date getDateActivated() {
        return dateActivated;
    }

    public void setDateActivated(Date dateActivated) {
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
