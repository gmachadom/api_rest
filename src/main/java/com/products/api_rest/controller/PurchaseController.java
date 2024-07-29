package com.products.api_rest.controller;

import com.products.api_rest.models.Purchase;
import com.products.api_rest.repository.PurchaseRepository;
import com.products.api_rest.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<?> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PostMapping()
    public ResponseEntity<?> addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.addPurchase(purchase);
    }

}
