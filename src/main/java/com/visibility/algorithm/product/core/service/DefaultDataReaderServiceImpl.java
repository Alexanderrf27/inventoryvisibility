package com.visibility.algorithm.product.core.service;

import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import com.visibility.algorithm.product.core.exeptions.WebRuntimeException;
import com.visibility.algorithm.product.core.ports.DefaultDataReaderService;
import com.visibility.algorithm.product.core.repository.ProductRepository;
import com.visibility.algorithm.product.core.repository.SizeRepository;
import com.visibility.algorithm.product.core.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This class provides the implementation for reading CSV data related to product, size and stock domains asynchronously
 * and saves them into their respective repositories.
 *
 * The '@Service' annotation is used in your service layer and instantiates, configures, and assembles the beans of your application.
 * Services hold business logic and call methods in the repository layer.
 */
@Service
@AllArgsConstructor
public class DefaultDataReaderServiceImpl implements DefaultDataReaderService {

    private final DefaultDataCsvReaderServiceImpl defaultDataReaderService;
    private final SizeRepository sizeRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    /**
     * This method reads data from CSV files asynchronously for ProductDomain, StockDomain, and SizeDomain,
     * then saves them into their respective repositories.
     *
     * @throws WebRuntimeException if any problem occurs while reading or saving the data.
     */
    public void saveDataVisibilityAlgorithm() {
        CompletableFuture<List<ProductDomain>> listProductDomain = defaultDataReaderService.readProductsAsync();
        CompletableFuture<List<StockDomain>> listStockDomain = defaultDataReaderService.readStocksAsync();
        CompletableFuture<List<SizeDomain>> listSizeDomain = defaultDataReaderService.readSizesAsync();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(listProductDomain, listStockDomain, listSizeDomain);

        try {
            allFutures.join();

            productRepository.saveAll(listProductDomain.get());
            sizeRepository.saveAll(listSizeDomain.get());
            stockRepository.saveAll(listStockDomain.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new WebRuntimeException("Error al guardar los datos", e);
        }
    }
}
