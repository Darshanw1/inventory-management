package com.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "stocks",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"product_id", "warehouse_id"}
        )
)

public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private int minimumStockLevel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Stock() {
    }

    public Stock(int quantity, Long id, int minimumStockLevel, Product product, Warehouse warehouse) {
        this.quantity = quantity;
        this.minimumStockLevel = minimumStockLevel;
        this.product = product;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(int minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}



























