package com.visibility.algorithm.product.core.domain.model;

import lombok.*;


/**
 * This class is used to encapsulate the response for the visibility of products.
 *
 * The '@Data' annotation provides getter, setter, equals, hashCode, and toString methods.
 *
 * The '@AllArgsConstructor' annotation provides a constructor with all arguments.
 *
 * The '@Builder' annotation produces complex builder APIs for your classes.
 *
 * The '@NoArgsConstructor' annotation provides a constructor with no arguments.
 *
 * The '@Getter' and '@Setter' annotations provide the getter and setter methods.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ProductsVisibilityResponse {

    /**
     * The ID of the product.
     */
    private Integer id;

    /**
     * The sequence of the product.
     */
    private int sequence;

    /**
     * This method provides a custom string representation of the ProductsVisibilityResponse object.
     *
     * @return A string representation of the ProductsVisibilityResponse object.
     */
    @Override
    public String toString() {
        return "ProductsPriceLastResponse{" +
                "id=" + id +
                ", sequence=" + sequence +
                '}';
    }
}

