package com.example.totcery_backend.domain;

public class Product {
    private Integer productId;
    private Integer userId;
    private Integer discountPrice;
    private String productName;
    private Integer originalPrice;
    private String locationRef;

    public Product(Integer productId, Integer userId, Integer discountPrice, String productName, Integer originalPrice, String locationRef) {
        this.productId = productId;
        this.userId = userId;
        this.discountPrice = discountPrice;
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.locationRef = locationRef;
    }
}
