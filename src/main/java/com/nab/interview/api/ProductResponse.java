package com.nab.interview.api;

import java.io.Serializable;

public class ProductResponse implements Serializable {


    private Long id;
    private String productName;
    private String productValue;
    private String productCategory;

    public ProductResponse() {

    }

    public ProductResponse(Long id, String productName, String productValue, String productCategory) {

        this.id = id;
        this.productName = productName;
        this.productValue = productValue;
        this.productCategory = productCategory;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
