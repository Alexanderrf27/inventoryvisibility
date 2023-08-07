package com.visibility.algorithm.product.api.delegate;


import com.visibility.algorithm.product.api.ProductManagerApiDelegate;
import com.visibility.algorithm.product.core.domain.model.ProductsVisibilityResponse;
import com.visibility.algorithm.product.core.ports.DefaultProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * This class serves as the API delegate for product management and provides advice for REST controllers.
 * <p>
 * The '@RestControllerAdvice' annotation is used to assist exception handling within a Controller.
 * This allows us to apply it across all Controllers within our application.
 * </p>
 */
@RestControllerAdvice
public class ProductApiDelegate implements ProductManagerApiDelegate {

    private final DefaultProductService defaultProductService;

    /**
     * This constructor creates an instance of the class and autowires the necessary service.
     *
     * @param defaultProductService - The service used to handle the default product price.
     */
    @Autowired
    public ProductApiDelegate(DefaultProductService defaultProductService) {
        this.defaultProductService = defaultProductService;
    }

    /**
     * This method retrieves the price of the last visible products.
     * <p>
     * If the last visible products' prices are not found, a 404 (NOT_FOUND) HTTP status is returned.
     * </p>
     *
     * @return A CompletableFuture that will be completed with a ResponseEntity containing a list of
     * ProductsVisibilityResponse if the visible products' prices are found or a 404 status otherwise.
     */
    public CompletableFuture<ResponseEntity<List<ProductsVisibilityResponse>>> getProductsVisibility() {
        return CompletableFuture.completedFuture(defaultProductService
                .getVisibleProducts()
                .map(lastResponse -> ResponseEntity.ok().body(lastResponse))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }
}
