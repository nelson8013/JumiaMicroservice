package com.hacknode.inventoryservice.Dtos.Requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryUpdateRequest {
    private String skuCode;
    private int quantityChange;
}
