package com.nab.interview.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.interview.JsonUtil;
import com.nab.interview.api.ProductResponse;
import com.nab.interview.exception.ServiceException;
import com.nab.interview.pojo.ProductEntity;
import com.nab.interview.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServicesTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    private IProductServices productServices;

    private String validResponsePath = "/allProductsresponse.json";
    private String productjson = "/productResponse.json";

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getAllProductsValidTest() {

        Mockito.when(productRepository.findAll())
                .thenReturn(JsonUtil.getListOfEntityFromJson(JsonUtil.readJsonFile(validResponsePath)));

        List<ProductResponse> response = productServices.getAllProducts();

        Assertions.assertEquals("fruit", response.get(0).getProductCategory());
        Assertions.assertEquals("madhu1", response.get(0).getProductName());

    }


    @Test
    public void getProductByCategoryValidTest() {

        Mockito.when(productRepository.findByCategory("test"))
                .thenReturn(JsonUtil.getListOfEntityFromJson(JsonUtil.readJsonFile(validResponsePath)));

        List<ProductResponse> response = productServices.getProductByCategory("test");

        Assertions.assertEquals("fruit", response.get(0).getProductCategory());
        Assertions.assertEquals("madhu1", response.get(0).getProductName());

    }


    @Test
    public void saveProductValidTest() {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductCategory("fruit");
        productEntity.setProductName("madhu1");
        productEntity.setProductValue("23");
        productEntity.setId(23l);

        Mockito.when(productRepository.save(Mockito.any()))
                .thenReturn(productEntity);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(null);

        ProductResponse response = productServices.createOrUpdateProduct(productResponse);

        Assertions.assertEquals("fruit", response.getProductCategory());
        Assertions.assertEquals("madhu1", response.getProductName());

    }

    @Test
    public void updateProductValidTest() {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductCategory("fruit");
        productEntity.setProductName("madhu1");
        productEntity.setProductValue("23");
        productEntity.setId(23l);

        Optional<ProductEntity> optional = Optional.of(productEntity);

        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(optional);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(productEntity);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(23l);
        productResponse.setProductCategory("fruit");
        productResponse.setProductName("madhu122");
        productResponse.setProductValue("23");

        ProductResponse response = productServices.createOrUpdateProduct(productResponse);

        Assertions.assertEquals("fruit", response.getProductCategory());
        Assertions.assertEquals("madhu122", response.getProductName());

    }


    @Test
    public void deleteProductThrowsExceptionTest() {

        Mockito.doThrow(new ServiceException("not deleted")).when(productRepository).deleteById(1l);

        Exception exception = Assertions.assertThrows(Exception.class, () -> productServices.deleteProductById(1l));
        Assertions.assertEquals("No Product exist for given id:1", exception.getMessage());
    }


}
