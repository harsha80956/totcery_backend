package com.example.totcery_backend.repositories;

import com.example.totcery_backend.domain.Address;
import com.example.totcery_backend.exceptions.TotResourceNotFoundException;

import java.util.List;

public interface AddressRepository {

    List<Address> findAll(Integer userId) throws TotResourceNotFoundException;

    Address findById(Integer userId, Integer addressId) throws TotResourceNotFoundException;

    Integer create(Integer userId, String state, String city, String area, String societyName, String respectiveBlock, String flatNumber, String parentAddress);



}
