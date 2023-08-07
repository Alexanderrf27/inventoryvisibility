package com.visibility.algorithm.product.core.exeptions;

import java.io.Serializable;

/**
 * The {@code ErrorCode} interface defines the structure for error code objects.
 * This interface mandates that all error code objects must be serializable and
 * have a {@code getCode} method that returns a string representation of the error code.
 * This can be useful for standardizing error responses in a system.
 */
public interface ErrorCode extends Serializable {

    /**
     * Retrieves the error code as a string.
     * This could be used for identifying the specific error that occurred.
     *
     * @return A string representation of the error code.
     */
    String getCode();
}
