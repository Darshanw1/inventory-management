package com.inventory.controller;

import com.inventory.entity.Stock;
import com.inventory.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    //GET all stock
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {

        return ResponseEntity.ok(
                stockService.getAllStock());
    }

    //GET stock by id
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(
            @PathVariable Long id) {

        return ResponseEntity.ok(stockService.getStockById(id));
    }

    //CREATE stock
    @PostMapping
    public ResponseEntity<Stock> createdStock(
            @RequestParam Long productId,
            @RequestParam Long warehouseId,
            @RequestParam int quantity,
            @RequestParam int minimumStockLevel) {

        return ResponseEntity.ok(stockService.creatStock(
                productId,
                warehouseId,
                quantity,
                minimumStockLevel
        ));
    }

    // Stock In
    @PutMapping("/{id}/stock-in")
    public ResponseEntity<Stock> stockIn(
            @PathVariable Long id,
            @RequestParam int quantity) {

        return ResponseEntity.ok(
                stockService.stockIn(id, quantity)
        );
    }

    // Stock Out
    @PutMapping("/{id}/stock-out")
    public ResponseEntity<Stock> stockOut(
            @PathVariable Long id,
            @RequestParam int quantity) {

        return ResponseEntity.ok(
                stockService.stockOut(id, quantity)
        );
    }

    // Delete stock
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(
            @PathVariable Long id) {

        stockService.deleteStock(id);

        return ResponseEntity.ok(
                "Stock deleted successfully"
        );
    }

}
