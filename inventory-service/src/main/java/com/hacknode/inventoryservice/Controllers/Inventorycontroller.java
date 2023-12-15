package com.hacknode.inventoryservice.Controllers;


import com.hacknode.inventoryservice.Dtos.Requests.InventoryUpdateRequest;
import com.hacknode.inventoryservice.Dtos.Responses.InventoryResponse;
import com.hacknode.inventoryservice.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class Inventorycontroller {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInstock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateInventoryQuantity(@RequestParam String skuCode, @RequestParam int quantityChange) {
        inventoryService.updateQuantity(skuCode, quantityChange);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/bulk-update")
    public ResponseEntity<Void> bulkUpdateInventory(@RequestBody List<InventoryUpdateRequest> updateRequests) {
        inventoryService.bulkUpdateInventory(updateRequests);
        return ResponseEntity.noContent().build();
    }
}
