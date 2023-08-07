package com.visibility.algorithm.product.core.service;

import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class DefaultDataCsvReaderServiceImplTest {
    @InjectMocks
    private DefaultDataCsvReaderServiceImpl sizeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sizeService = new DefaultDataCsvReaderServiceImpl();
    }

    @Test
    @DisplayName("Test ReadSizes  Success")
    void testReadSizesAsyncSuccess() throws Exception {
        List<SizeDomain> result = sizeService.readSizesAsync().get();

        assertEquals(17, result.size());
        assertEquals(11, result.get(0).getId());
        assertEquals(1, result.get(0).getProductId());
        Assertions.assertFalse(result.get(0).isSpecial());
        Assertions.assertTrue(result.get(0).isBackSoon());

        assertEquals(12, result.get(1).getId());
        assertEquals(1, result.get(1).getProductId());
        Assertions.assertFalse(result.get(1).isSpecial());
        Assertions.assertFalse(result.get(1).isBackSoon());
    }

    @Test
    @DisplayName("Test Read Products Async Success")
    void testReadProductsAsyncSuccess() throws Exception {
        List<ProductDomain> result = sizeService.readProductsAsync().get();

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(10, result.get(0).getSequence());
        assertEquals(2, result.get(1).getId());
        assertEquals(7, result.get(1).getSequence());
    }

    @Test
    @DisplayName("Test  Returns Lis tOf StockDomain")
    void testReadStocksAsync_ValidCsv_ReturnsListOfStockDomain() throws IOException {
        CompletableFuture<List<StockDomain>> futureResult = sizeService.readStocksAsync();
        List<StockDomain> result = futureResult.join();

        assertEquals(15, result.size());
        assertEquals(11, result.get(0).getSizeId());
        assertEquals(0, result.get(0).getQuantity());
        assertEquals(12, result.get(1).getSizeId());
        assertEquals(0, result.get(1).getQuantity());
    }
}
