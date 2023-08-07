package com.visibility.algorithm.product.api.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * This is a test class for ApiLayerConfig. The tests are run with the SpringExtension.
 */
@ExtendWith(SpringExtension.class)
class ApiLayerConfigTest {

    /**
     * Tests the validity of getters and setters for the ApiLayerConfig class.
     *
     * Using {@code assertThat} from Hamcrest to check the correctness of the
     * class properties' getter and setter methods.
     *
     * All of the conditions in the allOf statement must be met for the test to pass.
     */
    @Test
    @DisplayName("Test Context ApiLayerConfig")
    void test() {
        assertThat(ApiLayerConfig.class,
                allOf(hasValidGettersAndSetters())
        );
    }

}
