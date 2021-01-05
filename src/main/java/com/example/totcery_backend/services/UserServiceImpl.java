package com.example.totcery_backend.services;

import com.example.totcery_backend.domain.User;
import com.example.totcery_backend.exceptions.totAuthException;
import com.example.totcery_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws totAuthException {
        if (email != null) email =email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String fullName, String email, String password, String mobileNumber,String userType) throws totAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(!pattern.matcher(email).matches())
            throw new totAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new totAuthException("Email already in use");
        Integer userId = userRepository.create(fullName, email,password, mobileNumber,userType);
        return userRepository.findById(userId);
    }
}
