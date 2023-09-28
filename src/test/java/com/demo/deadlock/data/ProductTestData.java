package com.demo.deadlock.data;

import com.demo.deadlock.entity.Product;

import java.util.List;

public class ProductTestData {

    public static List<Product> getProducts() {
        return List.of(
                Product.builder().name("Chocolate").build(),
                Product.builder().name("Chocolate").build(),
                Product.builder().name("Book").build()
        );
    }
}
