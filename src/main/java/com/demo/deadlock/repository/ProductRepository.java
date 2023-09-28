package com.demo.deadlock.repository;

import com.demo.deadlock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Product loadProductById(@Param("id") Long id);

    @Query("select p from Product p where p.name = :name and p.id != :id")
    List<Product> findSimilarProducts(@Param("name") String name, @Param("id") Long id);
}
