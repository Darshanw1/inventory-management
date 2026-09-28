package com.inventory.dto;

public class PurchaseItemResponse {

    private Long productId;
    private String productName;
    private String sku;
    private int quantity;
    private double purchasePrice;

    public PurchaseItemResponse() {
    }

    public PurchaseItemResponse(
            Long productId,
            String productName,
            String sku,
            int quantity,
            double purchasePrice) {

        this.productId = productId;
        this.productName = productName;
        this.sku = sku;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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