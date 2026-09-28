package com.inventory.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseResponse {

    private Long purchaseId;
    private LocalDateTime purchaseDate;
    private String status;

    private Long supplierId;
    private String supplierName;

    private Long warehouseId;
    private String warehouseName;

    private List<PurchaseItemResponse> items;

    public PurchaseResponse() {
    }

    public PurchaseResponse(
            Long purchaseId,
            LocalDateTime purchaseDate,
            String status,
            Long supplierId,
            String supplierName,
            Long warehouseId,
            String warehouseName,
            List<PurchaseItemResponse> items) {

        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.items = items;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<PurchaseItemResponse> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemResponse> items) {
        this.items = items;
    }
}