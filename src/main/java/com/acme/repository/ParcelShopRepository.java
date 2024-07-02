package com.acme.repository;

import com.acme.models.ParcelShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link ParcelShop} entities.
 * Extends {@link MongoRepository} to provide CRUD operations and custom query methods.
 */
@Repository
public interface ParcelShopRepository extends MongoRepository<ParcelShop, String> {
}
