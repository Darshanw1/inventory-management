package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    //CREATE product
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid
            @RequestBody Product product ) {

        return ResponseEntity.ok(
                productService.createProduct(product)
        );
    }

    //UPDATE product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @Valid @RequestBody Product product) {

        System.out.println("========== UPDATE ==========");
        System.out.println("ID received: " + id);
        System.out.println("Product name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("SKU: " + product.getSku());
//        System.out.println("Quantity: " + product.getQuantity());


        return ResponseEntity.ok(
                productService.updateProduct(id, product)
        );
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully");
    }
}



