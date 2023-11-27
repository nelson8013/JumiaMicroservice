package com.hacknode.inventoryservice.Services;

import com.hacknode.inventoryservice.Dtos.Responses.InventoryResponse;
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
                            .build()
                ).toList();
    }
}
