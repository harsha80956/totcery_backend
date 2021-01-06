package com.example.totcery_backend.domain;

public class Address {

    private Integer addressId;
    private Integer userId;
    private String state;
    private String city;
    private String area;
    private String societyName;
    private String respectiveBlock;
    private String flatNumber;
    private String parentAddress;


    public Address(Integer addressId, Integer userId, String state, String city, String area, String societyName, String respectiveBlock, String flatNumber, String parentAddress) {
        this.addressId = addressId;
        this.userId = userId;
        this.state = state;
        this.city = city;
        this.area = area;
        this.societyName = societyName;
        this.respectiveBlock = respectiveBlock;
        this.flatNumber = flatNumber;
        this.parentAddress = parentAddress;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getRespectiveBlock() {
        return respectiveBlock;
    }

    public void setRespectiveBlock(String respectiveBlock) {
        this.respectiveBlock = respectiveBlock;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }
}
