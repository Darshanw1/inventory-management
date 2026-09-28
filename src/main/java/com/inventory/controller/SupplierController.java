package com.inventory.controller;

import com.inventory.entity.Supplier;
import com.inventory.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //GET all suppliers
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    //GET supplier by id
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(
            @PathVariable Long id) {

        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    //Create supplier
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(
            @RequestBody Supplier supplier) {

        return ResponseEntity.ok(supplierService.createSupplier(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable Long id,
            @RequestBody Supplier supplier) {

        return ResponseEntity.ok(
                supplierService.updateSupplier(id, supplier)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(
            @PathVariable Long id) {

        supplierService.deleteSupplier(id);

        return ResponseEntity.ok(
                "Supplier deleted successfully"
        );
    }
}
