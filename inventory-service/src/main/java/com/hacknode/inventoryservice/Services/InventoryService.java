package com.hacknode.inventoryservice.Services;

import com.hacknode.inventoryservice.Dtos.Requests.InventoryUpdateRequest;
import com.hacknode.inventoryservice.Dtos.Responses.InventoryResponse;
import com.hacknode.inventoryservice.Exceptions.InsufficientInventoryException;
import com.hacknode.inventoryservice.Exceptions.ProductNotFoundException;
import com.hacknode.inventoryservice.Model.Inventory;
import com.hacknode.inventoryservice.Repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCode)  {

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map( inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .availableQuantity(inventory.getQuantity())
                            .build()
                ).toList();
    }

    public void updateQuantity(String skuCode, int quantityChange) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        int updatedQuantity = inventory.getQuantity() + quantityChange;
        if (updatedQuantity < 0) {
            throw new InsufficientInventoryException("Insufficient inventory for product with SKU code: " + skuCode);
        }

        inventory.setQuantity(updatedQuantity);

        inventoryRepository.save(inventory);
    }

    public void bulkUpdateInventory(List<InventoryUpdateRequest> updateRequests) {
        for (InventoryUpdateRequest request : updateRequests) {
            updateQuantity(request.getSkuCode(), request.getQuantityChange());
        }
    }
}
