package com.example.totcery_backend.resources;

import com.example.totcery_backend.domain.Address;
import com.example.totcery_backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @GetMapping("")
    public String getAllAddress(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated UserID" + userId;
    }

    @PostMapping("")
    public ResponseEntity<Address> addAddress(HttpServletRequest request,
                                              @RequestBody Map<String, Object> addressMap){
        int userId = (Integer) request.getAttribute("userId");
        String state = (String) addressMap.get("state");
        String city = (String) addressMap.get("city");
        String area = (String) addressMap.get("area");
        String societyName = (String) addressMap.get("societyName");
        String respectiveBlock = (String) addressMap.get("respectiveBlock");
        String flatNumber = (String) addressMap.get("flatNumber");
        String parentAddress = (String) addressMap.get("parentAddress");
        Address address = addressService.addAddress(userId, state, city, area, societyName, respectiveBlock, flatNumber, parentAddress);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
}
