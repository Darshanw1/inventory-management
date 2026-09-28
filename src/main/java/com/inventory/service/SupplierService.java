package com.inventory.service;

import com.inventory.entity.Supplier;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    //Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    //get supplier by id
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    //create supplier
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    //update supplier
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {

        Supplier existingSupplier = getSupplierById(id);

        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setPhone(updatedSupplier.getPhone());
        existingSupplier.setAddress(updatedSupplier.getAddress());

        return supplierRepository.save(existingSupplier);
    }

    //Delete supplier
    public void deleteSupplier(Long id) {

        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found");
        }

        supplierRepository.deleteById(id);
    }

}
