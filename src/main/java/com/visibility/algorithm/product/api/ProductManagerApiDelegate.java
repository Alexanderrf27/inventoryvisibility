package com.visibility.algorithm.product.api;


import com.visibility.algorithm.product.api.config.ApiLayerConfig;
import com.visibility.algorithm.product.core.domain.model.ProductsVisibilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This interface serves as the API delegate for product management.
 *
 * The '@RestController' annotation is used to mark this interface as a Restful Controller,
 * meaning it's used in a RESTful web service.
 *
 * The '@RequestMapping' annotation maps HTTP requests to handler methods of MVC and REST controllers.
 */
@RestController
@RequestMapping(ApiLayerConfig.BASE_PATH)
public interface ProductManagerApiDelegate {

    /**
     * This method is a HTTP POST endpoint that retrieves the price of the last visible products.
     *
     * The '@Operation' annotation provides a description for the Swagger documentation.
     *
     * The '@PostMapping' annotation is used to map HTTP POST requests onto this method.
     *
     * The '@ResponseStatus' annotation marks the method with the status code and the reason message that
     * should be returned to the user.
     *
     * @return A CompletableFuture that will be completed with a ResponseEntity containing a list of
     * ProductsVisibilityResponse if the visible products' prices are found or a 404 status otherwise.
     */
    @Operation(description = "When a grid of products is painted on the web fronts of e-commerce stores, it is necessary to filter those products that have run out of stock to make it easier for the user to find products that they can buy.")
    @PostMapping(ApiLayerConfig.POST)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<ResponseEntity<List<ProductsVisibilityResponse>>> getProductsVisibility();

}

