package com.example.totcery_backend.services;

import com.example.totcery_backend.domain.User;
import com.example.totcery_backend.exceptions.totAuthException;

public interface UserService {

    User validateUser(String email, String password) throws totAuthException;

    User registerUser(String fullName, String email,String password, String mobileNumber, String userType) throws totAuthException;
}
