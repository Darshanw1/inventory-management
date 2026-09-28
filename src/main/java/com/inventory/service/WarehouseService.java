package com.inventory.service;

import com.inventory.entity.Warehouse;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //Get all Warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    //Get warehouse by ID
    public Warehouse getWarehouseById(Long id) {

        return warehouseRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Warehouse not found with id: " + id));
    }

    //Create WareHouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    //Update WareHouse
    public Warehouse updateWarehouse(
            Long id,
            Warehouse updatedWarehouse) {

        Warehouse existingWarehouse = getWarehouseById(id);

        existingWarehouse.setName(updatedWarehouse.getName());
        existingWarehouse.setLocation(updatedWarehouse.getLocation());
        existingWarehouse.setManagerName((updatedWarehouse.getManagerName()));

        return warehouseRepository.save(existingWarehouse);
    }


    //Delete warehouse
    public void deleteWarehouse(Long id) {

        if (!warehouseRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Warehouse not found with id: " + id
            );
        }

        warehouseRepository.deleteById(id);
    }
}
