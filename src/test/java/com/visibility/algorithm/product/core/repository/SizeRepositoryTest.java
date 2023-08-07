package com.visibility.algorithm.product.core.repository;

import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class SizeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SizeRepository sizeRepository;

    @Test
    @DisplayName("Test When SaveSize then Retrieve SameSize")
    void tesWhenSaveSize_thenRetrieveSameSize() {
        SizeDomain size = new SizeDomain();
        size.setId(1);
        size.setProductId(3);
        size.setSpecial(true);
        size.setBackSoon(true);


        entityManager.persist(size);
        entityManager.flush();

        Optional<SizeDomain> found = sizeRepository.findById(String.valueOf(size.getId()));
        assertTrue(found.isPresent());
    }

}
