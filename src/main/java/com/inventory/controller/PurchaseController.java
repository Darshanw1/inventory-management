package com.inventory.controller;

import com.inventory.dto.PurchaseRequest;
import com.inventory.dto.PurchaseResponse;
import com.inventory.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase(
            @Valid @RequestBody PurchaseRequest request) {

        return ResponseEntity.ok(
                purchaseService.createPurchase(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> getAllPurchases() {

        return ResponseEntity.ok(
                purchaseService.getAllPurchases()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> getPurchaseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                purchaseService.getPurchaseById(id)
        );
    }
}