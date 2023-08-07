package com.visibility.algorithm.product.core.ports;

import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * This interface provides service methods for reading CSV data related to product, size and stock domains asynchronously.
 *
 * The '@Service' annotation is used in your service layer and instantiates, configures, and assembles the
 * beans of your application. Services hold business logic and call methods in the repository layer.
 */
@Service
public interface DefaultCsvDataReaderService {

    /**
     * This method reads data related to products asynchronously.
     *
     * @return A CompletableFuture that will be completed with a list of ProductDomain instances once the data is read.
     */
    CompletableFuture<List<ProductDomain>> readProductsAsync();

    /**
     * This method reads data related to sizes asynchronously.
     *
     * @return A CompletableFuture that will be completed with a list of SizeDomain instances once the data is read.
     */
    CompletableFuture<List<SizeDomain>> readSizesAsync();

    /**
     * This method reads data related to stocks asynchronously.
     *
     * @return A CompletableFuture that will be completed with a list of StockDomain instances once the data is read.
     */
    CompletableFuture<List<StockDomain>> readStocksAsync();
}
