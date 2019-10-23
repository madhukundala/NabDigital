package com.nab.interview.services;

import com.nab.interview.api.ProductResponse;

import java.util.List;

public interface IProductServices {

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getProductByCategory(String category);

    ProductResponse createOrUpdateProduct(ProductResponse productResponse);

    String deleteProductById(Long id);

}
