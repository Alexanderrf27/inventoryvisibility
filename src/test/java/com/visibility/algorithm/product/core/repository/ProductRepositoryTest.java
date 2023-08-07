package com.visibility.algorithm.product.core.repository;

import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private ProductDomain product;

    @BeforeEach
    public void setUp() {
        product = new ProductDomain();
        product.setId(1);
        product = entityManager.persist(product);
    }

    @AfterEach
    public void tearDown() {
        entityManager.clear();
    }

    @Test
    @DisplayName("Test Context Save Product")
    void testSaveProduct() {
        ProductDomain savedProduct = productRepository
                .save(ProductDomain
                        .builder()
                        .id(5)
                        .sequence(3)
                        .build());

        assertTrue(savedProduct.getId() != null);
    }

    @Test
    @DisplayName("Test Context FindById")
    void testFindById() {
        Optional<ProductDomain> foundProduct = productRepository.findById(Long.valueOf(product.getId()));
        assertTrue(foundProduct.isPresent());
        assertEquals(product.getId(), foundProduct.get().getId());
    }

    @Test
    @DisplayName("Test Context DeleteProduct")
    void testDeleteProduct() {
        productRepository.delete(product);
        Optional<ProductDomain> deletedProduct = productRepository.findById(Long.valueOf(product.getId()));
        assertFalse(deletedProduct.isPresent());
    }

}
