package com.example.totcery_backend.services;

import com.example.totcery_backend.domain.Address;
import com.example.totcery_backend.exceptions.TotResourceNotFoundException;
import com.example.totcery_backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;
    @Override
    public List<Address> fetchAllAddress(Integer userId) {
        return null;
    }

    @Override
    public Address fetchAddressById(Integer userId, Integer AddressId) throws TotResourceNotFoundException {
        return null;
    }

    @Override
    public Address addAddress(Integer userId, String state, String city, String area, String societyName, String respectiveBlock, String flatNumber, String parentAddress) {
        int AddressId = addressRepository.create(userId, state, city, area, societyName, respectiveBlock, flatNumber, parentAddress);
        return addressRepository.findById(userId, AddressId);
    }
}
