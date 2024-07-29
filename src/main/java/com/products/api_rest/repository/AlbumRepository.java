package com.products.api_rest.repository;

import com.products.api_rest.models.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    List<Album> findAll();

    Album findById(int id);

    List<Album> findByTitle(String title);

    List<Album> findByOrderByTitle();

    @Query(value = "SELECT * FROM albums ORDER BY artist, released", nativeQuery = true)
    List<Album> findByOrderByArtistYear();

    List<Album> findByOrderByGenre();

    List<Album> findByOrderByReleased();

    List<Album> findByOrderByStock();

    List<Album> findByOrderByPrice();

    @Query(value = "SELECT * FROM albums WHERE artist = :artist ORDER BY released", nativeQuery = true)
    List<Album> findByArtist(String artist);

    List<Album> findByGenre(String genre);

    List<Album> findByReleased(int year);

    @Query(value = "SELECT * FROM albums WHERE stock <= :stock", nativeQuery = true)
    List<Album> findByStockLessThan(int stock);

    @Query(value = "SELECT * FROM albums WHERE price <= :price", nativeQuery = true)
    List<Album> findByPriceLessThan(BigDecimal price);

    @Override
    void delete(Album album);

    int countAlbumById(int id);

    int countAlbumByTitle(String title);

    int countAlbumByArtist(String artist);

    int countAlbumByGenre(String genre);

    int countAlbumByReleased(int year);
}
