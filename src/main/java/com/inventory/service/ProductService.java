package com.inventory.service;

import com.inventory.entity.Product;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id
                        )
                );
    }

    //Create Product
     public Product createProduct(Product product) {
        return productRepository.save(product);
     }

     //Update Product
     public Product updateProduct(Long id, Product updateProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(updateProduct.getName());
        existingProduct.setSku(updateProduct.getSku());
        existingProduct.setPrice(updateProduct.getPrice());

        return productRepository.save(existingProduct);
     }

     //Delete Product
     public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        productRepository.deleteById(id);
     }
}
