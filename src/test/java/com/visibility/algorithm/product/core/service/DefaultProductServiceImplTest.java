package com.visibility.algorithm.product.core.service;

import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import com.visibility.algorithm.product.core.domain.model.ProductsVisibilityResponse;
import com.visibility.algorithm.product.core.ports.DefaultDataReaderService;
import com.visibility.algorithm.product.core.repository.ProductRepository;
import com.visibility.algorithm.product.core.repository.SizeRepository;
import com.visibility.algorithm.product.core.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class DefaultProductServiceImplTest {


    @Mock
    private DefaultDataReaderService defaultDataReaderService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private DefaultProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new DefaultProductServiceImpl(defaultDataReaderService, productRepository, sizeRepository, stockRepository);
    }

    @Test
    @DisplayName("Test ShouldSave DataVisibility Algorithm Successfully")
    void TestGetVisibleProductsTest() {
        // Given
        ProductDomain product1 = new ProductDomain();
        product1.setId(1);
        product1.setSequence(2);

        ProductDomain product2 = new ProductDomain();
        product2.setId(2);
        product2.setSequence(3);

        SizeDomain size1 = new SizeDomain();
        size1.setId(1);
        size1.setProductId(1);
        size1.setSpecial(true);
        size1.setBackSoon(true);

        SizeDomain size2 = new SizeDomain();
        size2.setId(2);
        size2.setProductId(1);
        size2.setSpecial(false);
        size2.setBackSoon(false);

        StockDomain stock1 = new StockDomain();
        stock1.setSizeId(1);
        stock1.setQuantity(10);

        StockDomain stock2 = new StockDomain();
        stock2.setSizeId(2);
        stock2.setQuantity(5);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        when(sizeRepository.findAll()).thenReturn(Arrays.asList(size1, size2));
        when(stockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        Optional<List<ProductsVisibilityResponse>> visibleProducts = productService.getVisibleProducts();

          assertEquals(2, visibleProducts.get().size());
        assertEquals(1, visibleProducts.get().get(0).getId());

        verify(defaultDataReaderService).saveDataVisibilityAlgorithm();
        verify(productRepository).findAll();
        verify(sizeRepository).findAll();
        verify(stockRepository).findAll();
    }

}
