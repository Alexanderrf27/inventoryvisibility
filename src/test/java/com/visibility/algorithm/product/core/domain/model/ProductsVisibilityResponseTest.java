package com.visibility.algorithm.product.core.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
class ProductsVisibilityResponseTest {

    @Test
    @DisplayName("Test Context ProductsVisibilityResponse")
    void test() {
        assertThat(ProductsVisibilityResponse.class,
                allOf(hasValidGettersAndSetters(),
                        hasValidBeanConstructor(),
                        hasValidBeanHashCode())
        );
    }

}
