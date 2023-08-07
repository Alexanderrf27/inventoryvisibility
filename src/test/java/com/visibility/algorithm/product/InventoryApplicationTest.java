package com.visibility.algorithm.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InventoryApplicationTest {

    /**
     * This test ensures that the Spring Application context loads correctly.
     *
     * @param context The Spring Web Application context.
     */
    @Test
    public void contextLoads(WebApplicationContext context) {
        assertNotNull(context);
    }
}
