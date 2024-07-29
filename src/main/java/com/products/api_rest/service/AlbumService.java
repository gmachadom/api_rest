package com.products.api_rest.service;

import com.products.api_rest.models.Album;
import com.products.api_rest.models.Message;
import com.products.api_rest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;

@Service
public class AlbumService {
    @Autowired
    private Message message;

    @Autowired
    private AlbumRepository albumRepository;

    // Get methods
    public ResponseEntity<?> getAllAlbums() {
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getAlbumById(int id) {
        if (albumRepository.countAlbumById(id) == 0) {
            message.setMessage(String.format("Album not found with id %d.", id));
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(albumRepository.findById(id), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAlbumByTitle(String title) {
        if (title.isEmpty()) {
            message.setMessage("Please enter a title.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (albumRepository.countAlbumByTitle(title) == 0) {
            message.setMessage(String.format("No album was found with title %s.", title));
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(albumRepository.findByTitle(title), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAlbumByArtist(String artist) {
        if (artist.isEmpty()) {
            message.setMessage("Please enter a artist name.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (albumRepository.countAlbumByArtist(artist) == 0) {
            message.setMessage(String.format("No album was found with artist %s.", artist));
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(albumRepository.findByArtist(artist), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAlbumByGenre(String genre) {
        if (genre.isEmpty()) {
            message.setMessage("Please enter a genre name.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (albumRepository.countAlbumByGenre(genre) == 0) {
            message.setMessage(String.format("No album was found with genre %s.", genre));
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(albumRepository.findByGenre(genre), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAlbumByReleased(int released) {
        if (released < 1900 || released > Year.now().getValue()) {
            message.setMessage("Invalid year.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (albumRepository.countAlbumByReleased(released) == 0) {
            message.setMessage(String.format("No album was found with released %d.", released));
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(albumRepository.findByReleased(released), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAlbumByStockLessThan(int stock) {
        if (stock < 0) {
            message.setMessage("Invalid stock quantity.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(albumRepository.findByStockLessThan(stock), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAlbumByPriceLessThan(BigDecimal price) {
        if (price.compareTo(new BigDecimal(0)) < 0) {
            message.setMessage("Invalid price.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(albumRepository.findByPriceLessThan(price), HttpStatus.OK);
        }
    }

    // Post methods
    public ResponseEntity<?> postAlbum(Album album) {
        if (album.getTitle().isEmpty() && album.getArtist().isEmpty()){
            message.setMessage("Album cannot have and empty title and empty artist name.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else if(album.getTitle().isEmpty()){
            message.setMessage("Album cannot have an empty title.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else if(album.getArtist().isEmpty()) {
            message.setMessage("Album cannot have an empty artist name.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getGenre().isEmpty()) {
            message.setMessage("Album must have a genre.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if(album.getReleased() < 1900 || album.getReleased() > Year.now().getValue()){
            message.setMessage("Invalid year.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getStock() < 0) {
            message.setMessage("Stock cannot be negative.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(albumRepository.save(album), HttpStatus.CREATED);
        }
    }


    // Order methods
    public ResponseEntity<?> orderAlbumByTitle() {
        return new ResponseEntity<>(albumRepository.findByOrderByTitle(), HttpStatus.OK);
    }

    public ResponseEntity<?> orderAlbumByArtistByYear() {
        return new ResponseEntity<>(albumRepository.findByOrderByArtistYear(), HttpStatus.OK);
    }

    public ResponseEntity<?> orderAlbumByGenre() {
        return new ResponseEntity<>(albumRepository.findByOrderByGenre(), HttpStatus.OK);
    }

    public ResponseEntity<?> orderAlbumByReleased() {
        return new ResponseEntity<>(albumRepository.findByOrderByReleased(), HttpStatus.OK);
    }

    public ResponseEntity<?> orderAlbumByStock() {
        return new ResponseEntity<>(albumRepository.findByOrderByStock(), HttpStatus.OK);
    }

    public ResponseEntity<?> orderAlbumByPrice() {
        return new ResponseEntity<>(albumRepository.findByOrderByPrice(), HttpStatus.OK);
    }

    // Update methods
    public ResponseEntity<?> updateAlbum(Album album) {
        if (albumRepository.countAlbumById(album.getId()) == 0) {
            message.setMessage("Album not found with id " + album.getId());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (album.getTitle().isEmpty() && album.getArtist().isEmpty()){
            message.setMessage("Album cannot have and empty title and empty artist name.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getTitle().isEmpty()) {
            message.setMessage("Album cannot have an empty title.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getArtist().isEmpty()) {
            message.setMessage("Album cannot have an empty artist name.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getGenre().isEmpty()) {
            message.setMessage("Album must have a genre.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getReleased() < 1900 || album.getReleased() > Year.now().getValue()) {
            message.setMessage("Invalid year.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getStock() < 0) {
            message.setMessage("Stock cannot be negative.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (album.getPrice().compareTo(new BigDecimal(0)) < 0){
            message.setMessage("Invalid price.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(albumRepository.save(album), HttpStatus.OK);
        }
    }

    // Delete methods
    public ResponseEntity<?> deleteAlbum(int id) {
        if (albumRepository.countAlbumById(id) == 0) {
            message.setMessage("Album not found with id " + id);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            Album album = albumRepository.findById(id);
            albumRepository.delete(album);

            message.setMessage("Album was deleted with id " + id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
