package com.demo.deadlock.service;

import com.demo.deadlock.entity.Product;
import com.demo.deadlock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.demo.deadlock.constant.ApplicationConstants.FINISHED;
import static com.demo.deadlock.constant.ApplicationConstants.IN_PROGRESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SimilarProductService similarProductService;
    private final ProductPropertyService productPropertyService;

    @Transactional
    public void executeSimilarityCheck(Long productId) {
        log.info("Product {} similarity check started.", productId);
        Product product = productRepository.loadProductById(productId);
//        Product product = productRepository.findById(productId).orElse(null);
        productPropertyService.setSimilarityCheck(product, IN_PROGRESS);
        similarProductService.findAndPersistSimilarProducts(product);
        productPropertyService.setSimilarityCheck(product, FINISHED);
        log.info("Product {} similarity check finished.", productId);
    }
}
