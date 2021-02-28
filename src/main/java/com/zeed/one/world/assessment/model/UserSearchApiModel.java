package com.zeed.one.world.assessment.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserSearchApiModel {

    private String id;

    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registeredDate;

    private Boolean verified;

    private Integer pageNo = 1;

    private Integer pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
