package com.visibility.algorithm.product.core.repository;



import com.visibility.algorithm.product.core.domain.entity.SizeDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * This interface serves as a repository for performing CRUD operations on SizeDomain instances.
 *
 * The '@Repository' annotation indicates that this interface is a "Repository", originally defined by
 * Domain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage, retrieval, and
 * search behavior which emulates a collection of objects".
 *
 * It extends the CrudRepository interface from Spring Data JPA, which provides methods for CRUD operations.
 */
@Repository
public interface SizeRepository extends CrudRepository<SizeDomain, String> {}
