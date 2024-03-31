package com.bk.springsecurity.Dto;

public class UpdateProfileRequest {
    private String profile;
    private String userId;

    // Getters and setters
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
