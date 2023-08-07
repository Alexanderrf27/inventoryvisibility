package com.visibility.algorithm.product.core.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
class SizeDomainTest {

    @Test
    @DisplayName("Test Context SizeDomain")
    void test() {
        assertThat(SizeDomain.class,
                allOf(hasValidGettersAndSetters(),
                        hasValidBeanConstructor(),
                        hasValidBeanToString(),
                        hasValidBeanHashCode())
        );
    }

}
