package com.nab.interview.controller;

import com.nab.interview.api.CategoryResponse;
import com.nab.interview.api.ProductResponse;
import com.nab.interview.services.IProductServices;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    Logger logger = Logger.getLogger(ProductController.class);
    @Autowired
    private IProductServices productServices;

    /**
     * @return
     */
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getProducts() {

        logger.debug("entering getProducts");
        List<ProductResponse> result = productServices.getAllProducts();
        return result;
    }


    /**
     * @param productResponse
     * @return
     */
    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse createOrUpdateProduct(@RequestBody ProductResponse productResponse) {

        logger.debug("entering createOrUpdateProduct");
        ProductResponse updated = productServices.createOrUpdateProduct(productResponse);
        return updated;
    }


    /**
     * @param id
     * @return
     */
    @DeleteMapping(value ="/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProduct(@PathVariable("id") Long id) {

        logger.debug("entering deleteProduct");
        return productServices.deleteProductById(id);
    }

    /**
     * @param category
     * @return
     */
    @GetMapping(value = "/products/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getProductsByCategory(@PathVariable("category") String category) {

        logger.debug("entering getProductsByCategory");
        List<ProductResponse> result = productServices.getProductByCategory(category);
        return result;
    }


    /**
     * @return
     */
    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryResponse> getCategoryList() {

        logger.debug("entering getCategoryList");
        List<CategoryResponse> result = productServices.getCategoryList();
        return result;
    }


}
