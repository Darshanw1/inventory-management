package com.inventory.service;

import com.inventory.entity.Product;
import com.inventory.entity.Stock;
import com.inventory.entity.Warehouse;
import com.inventory.exception.DuplicateResourceException;
import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.StockRespository;
import com.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRespository stockRespository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockService(StockRespository stockRespository, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.stockRespository = stockRespository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    //get all stock
    public List<Stock> getAllStock() {
        return stockRespository.findAll();
    }

    //get stock by id
    public Stock getStockById(Long id) {
        return stockRespository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stock not found by id: " + id));

    }

    //Create stock
    public Stock creatStock(
            Long productId,
            Long warehouseId,
            int quantity,
            int minimumStockLevel) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Stock quantity must be greater than 0"
            );
        }

        if (minimumStockLevel < 0) {
            throw new IllegalArgumentException(
                    "Minimum stock level cannot be negative"
            );
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        if (stockRespository
                .findByProductAndWarehouse(product, warehouse)
                .isPresent()) {

            throw new DuplicateResourceException(
                    "Stock already exists for this product and warehouse"
            );
        }

        Stock stock = new Stock();

        stock.setProduct(product);
        stock.setWarehouse(warehouse);
        stock.setQuantity(quantity);
        stock.setMinimumStockLevel(minimumStockLevel);

        return stockRespository.save(stock);
    }

    // Stock In
    public Stock stockIn(Long stockId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Stock-in quantity must be greater than 0"
            );
        }

        Stock stock = getStockById(stockId);

        stock.setQuantity(
                stock.getQuantity() + quantity
        );

        return stockRespository.save(stock);
    }

    //Stock out
    public Stock stockOut(Long stockId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Stock-out quantity must be greater than 0"
            );
        }

        Stock stock = getStockById(stockId);

        if (stock.getQuantity() < quantity) {

            throw new InsufficientStockException(
                    "Insufficient stock. Available: "
                            + stock.getQuantity()
                            + ", requested: "
                            + quantity
            );
        }

        stock.setQuantity(
                stock.getQuantity() - quantity
        );

        return stockRespository.save(stock);
    }

    //Delete stock
    public void deleteStock(Long id) {

        if (!stockRespository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with id: " + id);
        }

        stockRespository.deleteById(id);
    }
}
































