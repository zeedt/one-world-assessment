package com.zeed.one.world.assessment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zeed.one.world.assessment.enums.Role;
import com.zeed.one.world.assessment.enums.Status;
import com.zeed.one.world.assessment.util.LocalDateTimeAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateRegistered;

    private boolean verified = false;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateVerified;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateDeactivated;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean deleted;

    private String title;

    @JsonIgnore
    private String approvalCode;

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

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(LocalDateTime dateVerified) {
        this.dateVerified = dateVerified;
    }

    public LocalDateTime getDateDeactivated() {
        return dateDeactivated;
    }

    public void setDateDeactivated(LocalDateTime dateActivated) {
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

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
