package com.inventory.controller;

import com.inventory.entity.Warehouse;
import com.inventory.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // GET all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(
                warehouseService.getAllWarehouses()
        );
    }

    //GET all Warehouse by ID
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    //CREATE warehouse
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(
            @RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouse));
    }

    //UPDATE Warehouse
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouseById(
            @PathVariable Long id,
            @RequestBody Warehouse warehouse) {

        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouse));
    }

    //DELETE Warehouse
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(
            @PathVariable Long id) {

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.ok("Warehouse deleted successfully");
    }
}
























