package com.visibility.algorithm.product.core.service;


import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import com.visibility.algorithm.product.core.domain.entity.StockDomain;
import com.visibility.algorithm.product.core.domain.model.ProductsVisibilityResponse;
import com.visibility.algorithm.product.core.ports.DefaultDataReaderService;
import com.visibility.algorithm.product.core.ports.DefaultProductService;
import com.visibility.algorithm.product.core.repository.ProductRepository;
import com.visibility.algorithm.product.core.repository.SizeRepository;
import com.visibility.algorithm.product.core.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class implements the DefaultProductService interface and provides functionality for retrieving and managing
 * the visibility of products. The visibility of products is determined based on product sizes and their stock availability.
 *
 * The '@Service' annotation is used in your service layer and instantiates, configures, and assembles the beans of your application.
 * Services hold business logic and call methods in the repository layer.
 *
 * The '@Slf4j' annotation is a convenience annotation for Lombok to automatically create a SLF4J Logger in the class.
 */
@Service
@Slf4j
public class DefaultProductServiceImpl implements DefaultProductService {


    private final DefaultDataReaderService defaultDataReaderService;

    private final ProductRepository productRepository;

    private final SizeRepository sizeRepository;

    private final StockRepository stockRepository;

    /**
     * Constructor for DefaultProductServiceImpl
     *
     * @param defaultDataReaderService Service to handle data reading operations.
     * @param productRepository Repository for managing ProductDomain entities.
     * @param sizeRepository Repository for managing SizeDomain entities.
     * @param stockRepository Repository for managing StockDomain entities.
     */
    @Autowired
    public DefaultProductServiceImpl(DefaultDataReaderService defaultDataReaderService, ProductRepository productRepository, SizeRepository sizeRepository, StockRepository stockRepository) {
        this.defaultDataReaderService = defaultDataReaderService;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Retrieves a list of visible products based on certain visibility criteria.
     *
     * This method first saves the visibility algorithm data, then finds all the products, sizes, and stocks from their
     * respective repositories. These entities are then processed to determine the visibility of each product.
     *
     * @return A list of visible products wrapped in an Optional. The Optional is empty if no visible products are found.
     */
    @Override
    public Optional<List<ProductsVisibilityResponse>> getVisibleProducts() {
        defaultDataReaderService.saveDataVisibilityAlgorithm();

        Iterable<ProductDomain> iterable = productRepository.findAll();
        List<ProductDomain> productList = StreamSupport.stream(iterable.spliterator(), false).toList();

        Iterable<SizeDomain> sizes = sizeRepository.findAll();
        List<SizeDomain> sizeDomainList = StreamSupport.stream(sizes.spliterator(), false).toList();


        Iterable<StockDomain> stocks = stockRepository.findAll();
        List<StockDomain> stockDomainList = StreamSupport.stream(stocks.spliterator(), false).toList();

        return Optional.of(getVisibleProductsAsync(productList, sizeDomainList, stockDomainList)
                .join()
                .stream()
                .toList());
    }

    /**
     * Determines the visibility of each product in an asynchronous manner.
     *
     * A product is considered visible if it has stock and if its sizes include both special and regular stock.
     * The visibility of products is returned as a list of ProductsVisibilityResponse entities, sorted by sequence.
     *
     * @param products The list of products to be processed.
     * @param sizes The list of sizes corresponding to the products.
     * @param stocks The list of stocks corresponding to the sizes.
     * @return A CompletableFuture that will provide a list of visible products.
     */
    private CompletableFuture<List<ProductsVisibilityResponse>> getVisibleProductsAsync(List<ProductDomain> products, List<SizeDomain> sizes, List<StockDomain> stocks) {
        return CompletableFuture.supplyAsync(() -> {
            Map<Integer, List<SizeDomain>> sizeMap = sizes.stream().collect(Collectors.groupingBy(SizeDomain::getProductId));
            Map<Integer, Integer> stockMap = stocks.stream().collect(Collectors.toMap(StockDomain::getSizeId, StockDomain::getQuantity));

            List<ProductDomain> visibleProducts = new ArrayList<>();
            for (ProductDomain product : products) {
                List<SizeDomain> productSizes = sizeMap.getOrDefault(product.getId(), Collections.emptyList());
                boolean hasStock = false;
                boolean hasSpecialStock = false;
                boolean hasRegularStock = false;

                for (SizeDomain size : productSizes) {
                    Integer quantity = stockMap.getOrDefault(size.getId(), 0);
                    if (quantity > 0 || size.isBackSoon()) {
                        hasStock = true;
                        if (size.isSpecial()) {
                            hasSpecialStock = true;
                        } else {
                            hasRegularStock = true;
                        }
                    }
                }
                if ((hasSpecialStock && hasRegularStock) ||
                        !(productSizes.stream().anyMatch(SizeDomain::isSpecial) || !hasRegularStock) ||
                        !(!productSizes.stream().allMatch(size -> !size.isSpecial() && (size.isBackSoon() || stockMap.getOrDefault(size.getId(), 0) > 0)) ||
                                productSizes.stream().anyMatch(SizeDomain::isSpecial))){

                    visibleProducts.add(product);
                }

            }
            return visibleProducts.stream()
                    .map(product -> ProductsVisibilityResponse
                            .builder()
                            .id(product.getId())
                            .sequence(product.getSequence())
                            .build())
                    .sorted(Comparator
                            .comparingInt(ProductsVisibilityResponse::getSequence))
                    .toList();
        });
    }
}

