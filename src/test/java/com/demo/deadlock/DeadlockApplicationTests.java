package com.demo.deadlock;

import com.demo.deadlock.component.AsyncRunner;
import com.demo.deadlock.data.ProductTestData;
import com.demo.deadlock.data.PropertyTestData;
import com.demo.deadlock.entity.ProductProperty;
import com.demo.deadlock.service.ProductService;
import com.demo.deadlock.util.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.demo.deadlock.constant.ApplicationConstants.FINISHED;
import static com.demo.deadlock.constant.ApplicationConstants.SIMILARITY_CHECK;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@SpringBootTest
class DeadlockApplicationTests extends AbstractIntegrationTest {

	@Autowired
	private AsyncRunner asyncRunner;

	@Autowired
	private ProductService productService;

	@BeforeEach
	void setUp() {
		dbHelper.saveProducts(ProductTestData.getProducts());
		dbHelper.saveProperty(PropertyTestData.getSimilarityCheckProperty());
	}

	@Test
	void deadLockTest() {
		asyncRunner.run(() -> productService.executeSimilarityCheck(1L));
		asyncRunner.run(() -> productService.executeSimilarityCheck(2L));

		waitUntilFinished();

		log.info("Deadlock avoided.");
	}

	private void waitUntilFinished() {
		int maxRetries = 5;
		int retriesCount = 0;

		while (retriesCount < maxRetries) {
			Optional<ProductProperty> productProperty = dbHelper.findProductProperty(SIMILARITY_CHECK, 1L)
					.filter(pp -> pp.getValue().equals(FINISHED));

			if (productProperty.isPresent()) {
				break;
			} else {
				retriesCount++;
				ApplicationUtils.sleep(1);
			}
		}

		while (retriesCount < maxRetries) {
			Optional<ProductProperty> productProperty = dbHelper.findProductProperty(SIMILARITY_CHECK, 2L)
					.filter(pp -> pp.getValue().equals(FINISHED));

			if (productProperty.isPresent()) {
				return;
			} else {
				retriesCount++;
				ApplicationUtils.sleep(1);
			}
		}

		fail("Deadlock error.");
	}
}
