package com.demo.deadlock.helper;

import com.demo.deadlock.entity.Product;
import com.demo.deadlock.entity.ProductProperty;
import com.demo.deadlock.entity.Property;
import com.demo.deadlock.entity.SimilarProduct;
import com.demo.deadlock.repository.ProductPropertyRepository;
import com.demo.deadlock.repository.ProductRepository;
import com.demo.deadlock.repository.PropertyRepository;
import com.demo.deadlock.repository.SimilarProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DbHelper {

    private final PropertyRepository propertyRepository;
    private final ProductRepository productRepository;
    private final ProductPropertyRepository productPropertyRepository;
    private final SimilarProductRepository similarProductRepository;

    @Transactional
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Transactional
    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Transactional
    public ProductProperty saveProductProperty(ProductProperty productProperty) {
        return productPropertyRepository.save(productProperty);
    }

    @Transactional
    public SimilarProduct saveSimilarProduct(SimilarProduct similarProduct) {
        return similarProductRepository.save(similarProduct);
    }

    @Transactional
    public Optional<ProductProperty> findProductProperty(String key, Long id) {
        return productPropertyRepository.findProductProperty(key, id);
    }
}
