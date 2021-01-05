package com.example.totcery_backend.repositories;

import com.example.totcery_backend.domain.User;
import com.example.totcery_backend.exceptions.totAuthException;

public interface UserRepository {

    Integer create(String fullName, String email, String password, String mobileNumber, String userType) throws totAuthException;

    User findByEmailAndPassword(String email, String password) throws totAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
