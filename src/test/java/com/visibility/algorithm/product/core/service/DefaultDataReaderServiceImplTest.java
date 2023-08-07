package com.visibility.algorithm.product.core.service;

import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import com.visibility.algorithm.product.core.repository.ProductRepository;
import com.visibility.algorithm.product.core.repository.SizeRepository;
import com.visibility.algorithm.product.core.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class DefaultDataReaderServiceImplTest {

    @InjectMocks
    private DefaultDataReaderServiceImpl service;

    @Mock
    private DefaultDataCsvReaderServiceImpl defaultDataReaderService;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new DefaultDataReaderServiceImpl(defaultDataReaderService,sizeRepository, stockRepository, productRepository);
    }

    @Test
    @DisplayName("Test ShouldSave DataVisibility Algorithm Successfully")
    void testShouldSaveDataVisibilityAlgorithmSuccessfully() {
        ProductDomain mockProduct = mock(ProductDomain.class);
        StockDomain mockStock = mock(StockDomain.class);
        SizeDomain mockSize = mock(SizeDomain.class);

        when(defaultDataReaderService.readProductsAsync())
                .thenReturn(CompletableFuture.completedFuture(Collections.singletonList(mockProduct)));
        when(defaultDataReaderService.readStocksAsync())
                .thenReturn(CompletableFuture.completedFuture(Collections.singletonList(mockStock)));
        when(defaultDataReaderService.readSizesAsync())
                .thenReturn(CompletableFuture.completedFuture(Collections.singletonList(mockSize)));

        service.saveDataVisibilityAlgorithm();

        verify(productRepository).saveAll(Collections.singletonList(mockProduct));
        verify(sizeRepository).saveAll(Collections.singletonList(mockSize));
        verify(stockRepository).saveAll(Collections.singletonList(mockStock));
    }

}
