package com.products.api_rest.repository;

import com.products.api_rest.models.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findAll();

    @Query(value = "SELECT * FROM purchases WHERE purchase_id = (SELECT purchase_id FROM album_purchase WHERE album_id = :albumId)", nativeQuery = true)
    List<Purchase> findByAlbumId(int albumId);

}
