package com.nab.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.interview.JsonUtil;
import com.nab.interview.api.ProductResponse;
import com.nab.interview.exception.ServiceException;
import com.nab.interview.services.IProductServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductServices productServices;

    @Autowired
    private ObjectMapper objectMapper;

    private String validResponsePath = "/allProductsresponse.json";
    private String productResponse = "/productResponse.json";

    @Test
    public void validGetAllProducts() throws Exception {

        Mockito.when(productServices.getAllProducts())
                .thenReturn(JsonUtil.getListOfObjectsFromJson(JsonUtil.readJsonFile(validResponsePath)));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());

        Mockito.verify(productServices, Mockito.times(1)).getAllProducts();

    }


    @Test
    public void validGetProductByCategory() throws Exception {

        Mockito.when(productServices.getProductByCategory(Mockito.anyString()))
                .thenReturn(JsonUtil.getListOfObjectsFromJson(JsonUtil.readJsonFile(validResponsePath)));

        mockMvc.perform(get("/api/products", "fruits"))
                .andExpect(status().isOk());

        Mockito.verify(productServices, Mockito.times(1)).getAllProducts();

    }


    @Test
    public void validPostCreateProduct() throws Exception {

        Mockito.when(productServices.createOrUpdateProduct(Mockito.any()))
                .thenReturn(JsonUtil.getObjectFromJson(JsonUtil.readJsonFile(productResponse), ProductResponse.class));

        ProductResponse response = new ProductResponse(null, "test", "test1", "fruit");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        Mockito.verify(productServices, Mockito.times(1)).createOrUpdateProduct(Mockito.any());

    }

    @Test
    public void validDeleteProductById() throws Exception {

        Mockito.when(productServices.deleteProductById(Mockito.anyLong()))
                .thenReturn("Deleted");

        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isOk());

        Mockito.verify(productServices, Mockito.times(1)).deleteProductById(Mockito.anyLong());

    }


    @Test
    public void validDeleteProductByIdException() throws Exception {

        Mockito.when(productServices.deleteProductById(Mockito.anyLong()))
                .thenThrow(new ServiceException("NOT DELETED"));

        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().is5xxServerError());

        Mockito.verify(productServices, Mockito.times(1)).deleteProductById(Mockito.anyLong());

    }


}
