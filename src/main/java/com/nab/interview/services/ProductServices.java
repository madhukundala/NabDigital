package com.nab.interview.services;

import com.nab.interview.api.ProductResponse;
import com.nab.interview.exception.ServiceException;
import com.nab.interview.pojo.ProductEntity;
import com.nab.interview.repository.ProductRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices implements IProductServices {

    Logger logger = Logger.getLogger(ProductServices.class);

    @Autowired
    ProductRepository productRepository;

    /**
     * @return
     */
    public List<ProductResponse> getAllProducts() {

        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductResponse> productResponses = null;
        if (productEntities != null && productEntities.size() > 0) {
            productResponses = new ArrayList<>();
            for (ProductEntity productEntity : productEntities) {
                ProductResponse productResponse = new ProductResponse(productEntity.getId(), productEntity.getProductName(), productEntity.getProductValue(), productEntity.getProductCategory());
                productResponses.add(productResponse);
            }
        }

        return productResponses;

    }

    /**
     * @param category
     * @return
     */
    public List<ProductResponse> getProductByCategory(String category) {

        List<ProductEntity> productEntities = productRepository.findByCategory(category);
        List<ProductResponse> productResponses = null;
        if (!CollectionUtils.isEmpty(productEntities)) {
            productResponses = new ArrayList<>();

            for (ProductEntity productEntity : productEntities) {

                ProductResponse productResponse = new ProductResponse(productEntity.getId(), productEntity.getProductName(), productEntity.getProductValue(), productEntity.getProductCategory());
                productResponses.add(productResponse);

            }
        } else {
            logger.error("No Product exist for given id");
            throw new ServiceException("No Product exist for given id");
        }
        return productResponses;
    }

    /**
     * @param response
     * @return
     */
    public ProductResponse createOrUpdateProduct(ProductResponse response) {


        ProductResponse productResponse = null;
        ProductEntity productEntity;
        if (response.getId() != null) {
            Optional<ProductEntity> productEntity1 = productRepository.findById(response.getId());

            if (productEntity1.isPresent()) {
                ProductEntity productUpdateEntity = productEntity1.get();
                productUpdateEntity.setProductValue(response.getProductValue());
                productUpdateEntity.setProductName(response.getProductName());
                productUpdateEntity.setProductCategory(response.getProductCategory());

                productEntity = productRepository.save(productUpdateEntity);
                productResponse = new ProductResponse(productEntity.getId(), productEntity.getProductName(), productEntity.getProductValue(), productEntity.getProductCategory());

            }
        } else {

            productEntity = new ProductEntity();
            productEntity.setProductCategory(response.getProductCategory());
            productEntity.setProductName(response.getProductName());
            productEntity.setProductValue(response.getProductValue());


            productEntity = productRepository.save(productEntity);

            productResponse = new ProductResponse(productEntity.getId(), productEntity.getProductName(), productEntity.getProductValue(), productEntity.getProductCategory());

        }
        return productResponse;
    }

    /**
     * @param id
     * @return
     */
    public String deleteProductById(Long id) {

        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            productRepository.deleteById(id);
            return "Product deleted";
        } else {
            logger.error("No record found in deleteProductById");
            throw new ServiceException("No Product exist for given id:" + id);
        }
    }

}


