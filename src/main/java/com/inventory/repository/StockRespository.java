package com.inventory.repository;

import com.inventory.entity.Product;
import com.inventory.entity.Stock;
import com.inventory.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRespository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductAndWarehouse(
            Product product,
            Warehouse warehouse
    );

    List<Stock> findByWarehouse(Warehouse warehouse);

    List<Stock> findByProduct(Product product);
}