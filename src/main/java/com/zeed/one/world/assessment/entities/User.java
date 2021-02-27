package com.zeed.one.world.assessment.entities;

import com.zeed.one.world.assessment.enums.Role;
import com.zeed.one.world.assessment.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator( name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Date dateRegistered;

    private boolean verified = false;

    private Date dateVerified;

    private Date dateDeactivated;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean deleted;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

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

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(Date dateVerified) {
        this.dateVerified = dateVerified;
    }

    public Date getDateDeactivated() {
        return dateDeactivated;
    }

    public void setDateDeactivated(Date dateActivated) {
        this.dateDeactivated = dateActivated;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
