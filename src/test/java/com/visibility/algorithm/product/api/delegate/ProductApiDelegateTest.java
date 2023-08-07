package com.visibility.algorithm.product.api.delegate;

import com.visibility.algorithm.product.core.domain.model.ProductsVisibilityResponse;
import com.visibility.algorithm.product.core.ports.DefaultProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductApiDelegateTest {

    @Mock
    private DefaultProductService productService;

    @InjectMocks
    private ProductApiDelegate productManagerApiDelegate;

    private List<ProductsVisibilityResponse> productsVisibilityResponses;

    @BeforeEach
    public void setup() {
        productsVisibilityResponses = Arrays.asList(
                new ProductsVisibilityResponse(1, 10),
                new ProductsVisibilityResponse(2, 20)
        );
    }

    @Test
    @DisplayName("Test Context get  Products Visibility ok Response")
    void getProductsVisibilityTest() throws ExecutionException, InterruptedException {
        when(productService.getVisibleProducts()).thenReturn(Optional.of(productsVisibilityResponses));

        CompletableFuture<ResponseEntity<List<ProductsVisibilityResponse>>> futureResult = productManagerApiDelegate.getProductsVisibility();

        ResponseEntity<List<ProductsVisibilityResponse>> responseEntity = futureResult.get();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productsVisibilityResponses, responseEntity.getBody());
    }
}






