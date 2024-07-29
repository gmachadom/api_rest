package com.products.api_rest.controller;

import com.products.api_rest.models.Album;
import com.products.api_rest.repository.AlbumRepository;
import com.products.api_rest.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @GetMapping()
    public ResponseEntity<?> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @PostMapping()
    public ResponseEntity<?> postAlbum(@RequestBody Album album) {
        return albumService.postAlbum(album);
    }

    @PutMapping()
    public ResponseEntity<?> updateAlbum(@RequestBody Album album) {
        return albumService.updateAlbum(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbumById(@PathVariable int id) {
        return albumService.deleteAlbum(id);
    }

    // Get albums by...
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable int id) {
        return albumService.getAlbumById(id);
    }

    @GetMapping("/title={albumTitle}")
    public ResponseEntity<?> getAlbumByTitle(@PathVariable String albumTitle) {
        return albumService.getAlbumByTitle(albumTitle);
    }

    @GetMapping("/artist={artistName}")
    public ResponseEntity<?> getAlbumByArtist(@PathVariable String artistName) {
        return albumService.getAlbumByArtist(artistName);
    }

    @GetMapping("/genre={genre}")
    public ResponseEntity<?> getAlbumByGenre(@PathVariable String genre) {
        return albumService.getAlbumByGenre(genre);
    }

    @GetMapping("/year={year}")
    public ResponseEntity<?> getAlbumByYear(@PathVariable int year) {
        return albumService.getAlbumByReleased(year);
    }

    @GetMapping("/stock={stock}")
    public ResponseEntity<?> getAlbumByStockLessThan(@PathVariable int stock) {
        return albumService.getAlbumByStockLessThan(stock);
    }

    @GetMapping("/price={price}")
    public ResponseEntity<?> getAlbumByPriceLessThan(@PathVariable BigDecimal price) {
        return albumService.getAlbumByPriceLessThan(price);
    }

    @GetMapping("/count")
    public long count() {
        return albumRepository.count();
    }

    // Order albums
    @GetMapping("/api/orderByTitle")
    public ResponseEntity<?> orderByTitle() {
        return albumService.orderAlbumByTitle();
    }

    @GetMapping("/api/orderByArtist")
    public ResponseEntity<?> orderByArtist() {
        return albumService.orderAlbumByArtistByYear();
    }

    @GetMapping("/api/orderByGenre")
    public ResponseEntity<?> orderByGenre() {
        return albumService.orderAlbumByGenre();
    }

    @GetMapping("/api/orderByYear")
    public ResponseEntity<?> orderByReleased() {
        return albumService.orderAlbumByReleased();
    }

    @GetMapping("/api/orderByStock")
    public ResponseEntity<?> orderByStock() {
        return albumService.orderAlbumByStock();
    }

    @GetMapping("/api/orderByPrice")
    public ResponseEntity<?> orderByPrice() {
        return albumService.orderAlbumByPrice();
    }
}
