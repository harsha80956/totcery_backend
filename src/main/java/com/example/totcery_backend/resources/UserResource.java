package com.example.totcery_backend.resources;


import com.example.totcery_backend.Constants;
import com.example.totcery_backend.domain.User;
import com.example.totcery_backend.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String fullName = (String) userMap.get("fullName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String mobileNumber = (String) userMap.get("mobileNumber");
        String userType = (String) userMap.get("userType");
        User user = userService.registerUser(fullName, email, password, mobileNumber, userType);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
//        System.out.print("ccccc" +user.getUserId() + user.getEmail() + user.getPassword() + user.getFullName() + user.getMobileNumber() + user.getUserType() + " ");
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("fullName", user.getFullName())
                .claim("mobileNumber", user.getMobileNumber())
                .claim("userType",user.getUserType())
                .compact();
        Map<String, String> map = new HashMap<>();

        map.put("token", token);
        return map;
    }
}
