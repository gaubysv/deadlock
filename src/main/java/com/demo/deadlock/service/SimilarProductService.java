package com.demo.deadlock.service;

import com.demo.deadlock.entity.Product;
import com.demo.deadlock.entity.SimilarProduct;
import com.demo.deadlock.repository.ProductRepository;
import com.demo.deadlock.repository.SimilarProductRepository;
import com.demo.deadlock.util.ApplicationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimilarProductService {

    private final ProductRepository productRepository;
    private final SimilarProductRepository similarProductRepository;

    @Transactional
    public void findAndPersistSimilarProducts(Product product) {
        similarProductRepository.deleteSimilarProducts(product.getId());

        List<Product> products = productRepository.findSimilarProducts(product.getName(), product.getId());

        List<SimilarProduct> similarProducts = new ArrayList<>();
        for (Product similarProduct : products) {
            log.info("Product {} is similar to {} ", similarProduct, product);

            similarProducts.add(
                    SimilarProduct.builder()
                            .product(product)
                            .similarProduct(similarProduct)
                            .build()
            );
            ApplicationUtils.sleep(1);
        }

        List<SimilarProduct> reverseSimilarProducts = new ArrayList<>();
        for (Product similarProduct : products) {
            reverseSimilarProducts.add(
                    SimilarProduct.builder()
                            .product(similarProduct)
                            .similarProduct(product)
                            .build()
            );
            ApplicationUtils.sleep(1);
        }

        similarProductRepository.saveAll(similarProducts);
        similarProductRepository.saveAll(reverseSimilarProducts);
    }
}
