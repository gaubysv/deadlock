package com.demo.deadlock.service;

import com.demo.deadlock.entity.Product;
import com.demo.deadlock.entity.ProductProperty;
import com.demo.deadlock.entity.Property;
import com.demo.deadlock.repository.ProductPropertyRepository;
import com.demo.deadlock.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.demo.deadlock.constant.ApplicationConstants.SIMILARITY_CHECK;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductPropertyService {

    private final PropertyRepository propertyRepository;
    private final ProductPropertyRepository productPropertyRepository;

    @Transactional
    public void setSimilarityCheck(Product product, String value) {
        ProductProperty productProperty = productPropertyRepository
                .findProductProperty(SIMILARITY_CHECK, product.getId())
                .orElseGet(() -> {
                    Property property = propertyRepository.getPropertyByKey(SIMILARITY_CHECK);
                    return ProductProperty.builder()
                            .property(property)
                            .product(product)
                            .value(value)
                            .build();
                });
        productProperty.setValue(value);
        log.info("Set product {} similarity check to {}", product, value);
        productPropertyRepository.saveAndFlush(productProperty);
    }
}
