package com.zeed.one.world.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserSearchResponseModel {

    @JsonProperty("users")
    private List<UserApiModel> userApiModels;

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalPageNumber;

    private Long totalElements;

    public List<UserApiModel> getUserApiModels() {
        return userApiModels;
    }

    public void setUserApiModels(List<UserApiModel> userApiModels) {
        this.userApiModels = userApiModels;
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

    public Integer getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(Integer totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
