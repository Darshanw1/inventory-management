package com.inventory.service;

import com.inventory.dto.PurchaseItemRequest;
import com.inventory.dto.PurchaseItemResponse;
import com.inventory.dto.PurchaseRequest;
import com.inventory.dto.PurchaseResponse;
import com.inventory.entity.Product;
import com.inventory.entity.Purchase;
import com.inventory.entity.PurchaseItem;
import com.inventory.entity.Stock;
import com.inventory.entity.Supplier;
import com.inventory.entity.Warehouse;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.PurchaseRepository;
import com.inventory.repository.StockRespository;
import com.inventory.repository.SupplierRepository;
import com.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final StockRespository stockRepository;

    public PurchaseService(
            PurchaseRepository purchaseRepository,
            SupplierRepository supplierRepository,
            WarehouseRepository warehouseRepository,
            ProductRepository productRepository,
            StockRespository stockRepository) {

        this.purchaseRepository = purchaseRepository;
        this.supplierRepository = supplierRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public PurchaseResponse createPurchase(PurchaseRequest request) {

        // 1. Find supplier
        Supplier supplier = supplierRepository
                .findById(request.getSupplierId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Supplier not found with id: "
                                        + request.getSupplierId()
                        )
                );

        // 2. Find warehouse
        Warehouse warehouse = warehouseRepository
                .findById(request.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id: "
                                        + request.getWarehouseId()
                        )
                );

        // 3. Create purchase
        Purchase purchase = new Purchase();

        purchase.setSupplier(supplier);
        purchase.setWarehouse(warehouse);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setStatus("COMPLETED");

        // 4. Process every purchase item
        for (PurchaseItemRequest itemRequest : request.getItems()) {

            // Find product
            Product product = productRepository
                    .findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found with id: "
                                            + itemRequest.getProductId()
                            )
                    );

            // Create purchase item
            PurchaseItem item = new PurchaseItem();

            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPurchasePrice(itemRequest.getPurchasePrice());
            item.setPurchase(purchase);

            purchase.getItems().add(item);

            // Find stock for this product + warehouse
            Stock stock = stockRepository
                    .findByProductAndWarehouse(
                            product,
                            warehouse
                    )
                    .orElse(null);

            // If stock doesn't exist, create it
            if (stock == null) {

                stock = new Stock();

                stock.setProduct(product);
                stock.setWarehouse(warehouse);
                stock.setQuantity(itemRequest.getQuantity());

                // Default minimum stock level
                stock.setMinimumStockLevel(10);

            } else {

                // Existing stock → increase quantity
                stock.setQuantity(
                        stock.getQuantity()
                                + itemRequest.getQuantity()
                );
            }

            // Save stock
            stockRepository.save(stock);
        }

        // 5. Save purchase
        Purchase savedPurchase =
                purchaseRepository.save(purchase);

        // 6. Convert Entity → DTO
        return convertToResponse(savedPurchase);
    }

    // Get all purchases
    public List<PurchaseResponse> getAllPurchases() {

        return purchaseRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get purchase by ID
    public PurchaseResponse getPurchaseById(Long id) {

        Purchase purchase = purchaseRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Purchase not found with id: " + id
                        )
                );

        return convertToResponse(purchase);
    }

    // Convert Purchase Entity → PurchaseResponse DTO
    private PurchaseResponse convertToResponse(
            Purchase purchase) {

        List<PurchaseItemResponse> itemResponses =
                purchase.getItems()
                        .stream()
                        .map(item ->
                                new PurchaseItemResponse(
                                        item.getProduct().getId(),
                                        item.getProduct().getName(),
                                        item.getProduct().getSku(),
                                        item.getQuantity(),
                                        item.getPurchasePrice()
                                )
                        )
                        .toList();

        return new PurchaseResponse(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getStatus(),

                purchase.getSupplier().getId(),
                purchase.getSupplier().getName(),

                purchase.getWarehouse().getId(),
                purchase.getWarehouse().getName(),

                itemResponses
        );
    }
}