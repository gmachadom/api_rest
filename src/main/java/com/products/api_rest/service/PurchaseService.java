package com.products.api_rest.service;

import com.products.api_rest.models.Album;
import com.products.api_rest.models.Message;
import com.products.api_rest.models.Purchase;
import com.products.api_rest.repository.AlbumRepository;
import com.products.api_rest.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private Message message;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private AlbumRepository albumRepository;

    // Get methods
    public ResponseEntity<?> getAllPurchases() {
        return new ResponseEntity<>(purchaseRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getPurchaseById(int id) {
        return new ResponseEntity<>(purchaseRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> getPurchaseByAlbum(int albumId) {
        return new ResponseEntity<>(purchaseRepository.findByAlbumId(albumId), HttpStatus.OK);
    }

    // Post method
    public ResponseEntity<?> addPurchase(Purchase purchase) {
        List<Album> albums = purchase.getAlbums();

        for (Album album : albums) {
            album.getPurchases().add(purchase);
        }
        return new ResponseEntity<>(purchaseRepository.save(purchase), HttpStatus.OK);
    }
}
