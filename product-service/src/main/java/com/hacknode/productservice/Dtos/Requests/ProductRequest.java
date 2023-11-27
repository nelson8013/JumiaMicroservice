package com.hacknode.productservice.Dtos.Requests;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductRequest {
 private String name;
 private String description;
 private BigDecimal price;
 
}
