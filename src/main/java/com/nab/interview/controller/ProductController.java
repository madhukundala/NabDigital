package com.nab.interview.controller;

import com.nab.interview.api.ProductResponse;
import com.nab.interview.services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductServices productServices;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getProducts() {

        List<ProductResponse> result = productServices.getAllProducts();
        return result;
    }


    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse createOrUpdateProduct(@RequestBody ProductResponse productResponse) {
        ProductResponse updated = productServices.createOrUpdateProduct(productResponse);
        return updated;
    }


    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productServices.deleteProductById(id);
    }

    @GetMapping(value = "/products/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getProductsByCategory(@PathVariable("category") String category) {

        List<ProductResponse> result = productServices.getProductByCategory(category);
        return result;
    }


}
