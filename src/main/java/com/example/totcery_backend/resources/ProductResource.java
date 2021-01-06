package com.example.totcery_backend.resources;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @GetMapping("")
    public String getAllProducts(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated: UserId: " + userId;
    }
}
