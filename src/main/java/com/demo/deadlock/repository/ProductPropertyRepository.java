package com.demo.deadlock.repository;

import com.demo.deadlock.entity.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {

    @Query("select pp from ProductProperty pp where pp.property.key = :key and pp.product.id = :id")
    Optional<ProductProperty> findProductProperty(@Param("key") String key, @Param("id") Long id);
}
