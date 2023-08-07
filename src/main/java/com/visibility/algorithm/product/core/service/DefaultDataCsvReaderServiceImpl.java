package com.visibility.algorithm.product.core.service;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import com.visibility.algorithm.product.core.exeptions.WebRuntimeException;
import com.visibility.algorithm.product.core.ports.DefaultCsvDataReaderService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * This class provides the implementation for reading CSV data related to product, size and stock domains asynchronously.
 *
 * The '@Service' annotation is used in your service layer and instantiates, configures, and assembles the beans of your application.
 * Services hold business logic and call methods in the repository layer.
 */
@Service
public class DefaultDataCsvReaderServiceImpl implements DefaultCsvDataReaderService {

    private static final String PRODUCT_CSV = "src/main/resources/product.csv";
    private static final String SIZE_CSV = "src/main/resources/size.csv";
    private static final String STOCK_CSV = "src/main/resources/stock.csv";


    /**
     * This method reads size data from a CSV file asynchronously.
     *
     * @return A CompletableFuture that will be completed with a list of SizeDomain instances once the data is read.
     * @throws WebRuntimeException if any problem occurs while reading the data.
     */
    @Override
    public CompletableFuture<List<SizeDomain>> readSizesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try (Reader reader = Files.newBufferedReader(Paths.get(SIZE_CSV));
                 CSVReader csvReader = new CSVReader(reader)) {
                List<SizeDomain> sizes = new ArrayList<>();
                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) {
                    sizes.add(SizeDomain.builder()
                            .id(Integer.parseInt(nextRecord[0]))
                            .productId(Integer.parseInt(nextRecord[1].trim()))
                            .backSoon(Boolean.parseBoolean(nextRecord[2].trim()))
                            .special(Boolean.parseBoolean(nextRecord[3].trim()))
                            .build());
                }
                return sizes;
            } catch (IOException | CsvValidationException e) {
                throw new WebRuntimeException("Failed to read sizes from CSV file", e);
            }
        });
    }

    /**
     * This method reads product data from a CSV file asynchronously.
     *
     * @return A CompletableFuture that will be completed with a list of ProductDomain instances once the data is read.
     * @throws WebRuntimeException if any problem occurs while reading the data.
     */
     @Override
    public CompletableFuture<List<ProductDomain>> readProductsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try (Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV));
                 CSVReader csvReader = new CSVReader(reader)) {

                List<ProductDomain> products = new ArrayList<>();
                String[] nextRecord;

                while ((nextRecord = csvReader.readNext()) != null) {
                    products.add(ProductDomain.builder()
                            .id(Integer.parseInt(nextRecord[0]))
                            .sequence(Integer.parseInt(nextRecord[1].trim()))
                            .build());
                }
                return products;
            } catch (IOException | CsvValidationException e) {
                throw new WebRuntimeException("Failed to read products from CSV file", e);
            }
        });
    }

    /**
     * This method reads stock data from a CSV file asynchronously.
     *
     * @return A CompletableFuture that will be completed with a list of StockDomain instances once the data is read.
     * @throws WebRuntimeException if any problem occurs while reading the data.
     */
    @Override
    public CompletableFuture<List<StockDomain>> readStocksAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try (Reader reader = Files.newBufferedReader(Paths.get(STOCK_CSV));
                 CSVReader csvReader = new CSVReader(reader)) {
                List<StockDomain> stocks = new ArrayList<>();
                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) stocks.add(StockDomain
                        .builder()
                        .sizeId(Integer.parseInt(nextRecord[0]))
                        .quantity(Integer.parseInt(nextRecord[1].trim()))
                        .build());
                return stocks;
            } catch (IOException | CsvValidationException e) {
                throw new WebRuntimeException("Failed to read stocks from CSV file", e);
            }
        });
    }
}
