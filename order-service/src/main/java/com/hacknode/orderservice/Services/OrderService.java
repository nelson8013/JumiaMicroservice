package com.hacknode.orderservice.Services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.hacknode.orderservice.Dtos.Responses.InventoryResponse;
import com.hacknode.orderservice.Exceptions.ProductNoLongerInStockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hacknode.orderservice.Dtos.OrderLineItemsDto;
import com.hacknode.orderservice.Dtos.Requests.OrderRequest;
import com.hacknode.orderservice.Model.Order;
import com.hacknode.orderservice.Model.OrderLineItems;
import com.hacknode.orderservice.Repositories.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

 private final OrderRepository orderRepository;

   private final WebClient.Builder webClientBuilder;

   public void placeOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    
    List<OrderLineItems> orderLineItems  = orderRequest.getOrderLineItemsDtoList()
    .stream()
    .map(this::mapToDto)
    .toList();

    order.setOrderLineItems(orderLineItems);

     /* Receive all the skuCodes from the order object * */

    List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();


    /*Before creating the order, make a call to the inventory service
    * and chcck if the product still exists
    *
    * */

     InventoryResponse[] inventoryResponseArray =  webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

     boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

     if(allProductsInStock) {
         orderRepository.save(order);
     } else {
         throw new ProductNoLongerInStockException("The requested product is no longer in stock");
     }
  }


  public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

    return orderLineItems;

  }
}