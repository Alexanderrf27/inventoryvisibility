package com.visibility.algorithm.product.core.ports;


import com.visibility.algorithm.product.core.domain.model.ProductsVisibilityResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This interface provides a service method for fetching the list of visible products.
 *
 * The '@Service' annotation is used in your service layer and instantiates, configures, and assembles the
 * beans of your application. Services hold business logic and call methods in the repository layer.
 */
@Service
public interface DefaultProductService {

    /**
     * This method retrieves a list of visible products.
     *
     * @return An Optional that may contain a list of ProductsVisibilityResponse instances.
     *         If no products are visible, an empty Optional is returned.
     */
    Optional<List<ProductsVisibilityResponse>> getVisibleProducts();
}

