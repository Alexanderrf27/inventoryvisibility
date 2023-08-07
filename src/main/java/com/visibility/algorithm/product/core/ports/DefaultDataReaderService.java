package com.visibility.algorithm.product.core.ports;

import org.springframework.stereotype.Service;

/**
 * This interface provides a service method for saving data using a specific visibility algorithm.
 *
 * The '@Service' annotation is used in your service layer and instantiates, configures, and assembles the
 * beans of your application. Services hold business logic and call methods in the repository layer.
 */
@Service
public interface DefaultDataReaderService {

    /**
     * This method handles the logic to save data based on a visibility algorithm.
     */
    void saveDataVisibilityAlgorithm();
}
