package com.demo.deadlock.repository;

import com.demo.deadlock.entity.SimilarProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SimilarProductRepository extends JpaRepository<SimilarProduct, Long> {

    @Modifying
    @Query("delete SimilarProduct sp where sp.product.id = :productId or sp.similarProduct.id = :productId")
    void deleteSimilarProducts(@Param("productId") Long productId);
}
