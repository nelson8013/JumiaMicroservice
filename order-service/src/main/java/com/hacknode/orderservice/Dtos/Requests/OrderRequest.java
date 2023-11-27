package com.hacknode.orderservice.Dtos.Requests;

import java.util.List;

import com.hacknode.orderservice.Dtos.OrderLineItemsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
 private List<OrderLineItemsDto> orderLineItemsDtoList;
 
}
