package com.visibility.algorithm.product.core.repository;


import com.visibility.algorithm.product.core.domain.entity.ProductDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * This interface serves as a repository for performing CRUD operations on ProductDomain instances.
 *
 * It extends the CrudRepository interface from Spring Data JPA, which provides methods for CRUD operations.
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductDomain, Long> {}
