package com.example.totcery_backend.services;

import com.example.totcery_backend.domain.Address;
import com.example.totcery_backend.exceptions.TotResourceNotFoundException;

import java.util.List;

public interface AddressService {

    List<Address> fetchAllAddress(Integer userId);

    Address fetchAddressById(Integer userId, Integer AddressId) throws TotResourceNotFoundException;

    Address addAddress(Integer userId, String state, String city, String area, String societyName, String respectiveBlock, String flatNumber, String parentAddress);


}
