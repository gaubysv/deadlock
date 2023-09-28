package com.demo.deadlock;

import com.demo.deadlock.helper.DbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@SpringBootTest
@ContextConfiguration(initializers = AbstractIntegrationTest.DatasourceInitializer.class)
public abstract class AbstractIntegrationTest {

    protected static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.5");

    static {
        postgreSQLContainer.setPortBindings(List.of("5432:5432"));
        postgreSQLContainer.start();
    }

    @Autowired
    protected DbHelper dbHelper;

    static class DatasourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
