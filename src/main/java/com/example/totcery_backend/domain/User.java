package com.example.totcery_backend.domain;

public class User {

    private Integer userId;
    private String fullName;
    private String email;
    private String password;
    private String mobileNumber;
    private String userType;

    public User(Integer userId, String fullName, String email, String password, String mobileNumber, String userType) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.userType = userType;
    }

    public Integer getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }
}
