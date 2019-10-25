package com.nab.interview.repository;

import com.nab.interview.pojo.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    @Query("SELECT u FROM ProductEntity u WHERE u.productCategory = :productCategory")
    List<ProductEntity> findProductByCategory(@Param("productCategory") String productCategory);


    @Query("SELECT DISTINCT u.productCategory FROM ProductEntity u")
    List<String> getCategoryList();

}
