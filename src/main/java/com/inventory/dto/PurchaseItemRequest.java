package com.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PurchaseItemRequest {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

    @Positive(message = "Purchase price must be greater than 0")
    private double purchasePrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}