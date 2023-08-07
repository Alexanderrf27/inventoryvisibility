package com.visibility.algorithm.product.core.repository;

import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockRepository stockRepository;

    @Test
    @DisplayName("Test When Stock then Retrieve Stock")
    void whenSaveStock_thenRetrieveSameStock() {
        StockDomain stock = new StockDomain();
        stock.setSizeId(2);
        stock.setQuantity(23);

        entityManager.persist(stock);
        entityManager.flush();

        Optional<StockDomain> found = stockRepository.findById(String.valueOf(stock.getSizeId()));
        assertTrue(found.isPresent());
    }
}
