package com.hacknode.orderservice.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.hacknode.orderservice.Dtos.InventoryUpdateRequest;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

   private final OrderRepository orderRepository;

   private final WebClient.Builder webClientBuilder;

   public void placeOrder(OrderRequest orderRequest){
    Order order = new Order();

    List<OrderLineItems> orderLineItems  = orderRequest.getOrderLineItemsDtoList()
               .stream()
               .map(this::mapToDto)
               .toList();
    order.setOrderLineItems(orderLineItems);


    /* Receive all the skuCodes from the order object * */
    List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();


    /*Before creating the order, make a call to the inventory service and check if the product still exists * */

    InventoryResponse[] inventoryResponseArray =
               webClientBuilder
                       .build()
                       .get()
                       .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                       .retrieve()
                       .bodyToMono(InventoryResponse[].class)
                       .block();


    boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
               .allMatch(response -> response.isInStock() && isQuantityAvailable(response, order));


    if(allProductsInStock) {

        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

       updateInventoryAfterOrder(orderRequest);
    } else {
       throw new ProductNoLongerInStockException("The requested product is no longer in stock");
    }

  }

    private boolean isQuantityAvailable(InventoryResponse response, Order order) {
        Optional<OrderLineItems> orderLineItemOptional = order.getOrderLineItems()
                .stream()
                .filter(item -> item.getSkuCode().equals(response.getSkuCode()))
                .findFirst();

        return orderLineItemOptional.map(orderLineItem -> orderLineItem.getQuantity() <= response.getAvailableQuantity()).orElse(false);
    }

    private void updateInventoryAfterOrder(OrderRequest orderRequest) {

        List<InventoryUpdateRequest> updateRequests = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItem  -> new InventoryUpdateRequest(orderLineItem.getSkuCode(), -orderLineItem.getQuantity()))
                .collect(Collectors.toList());

        webClientBuilder
                .build()
                .put()
                .uri("http://inventory-service/api/inventory/bulk-update")
                .body(BodyInserters.fromValue(updateRequests))
                .retrieve()
                .toBodilessEntity()
                .block();
    }


  public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

    return orderLineItems;

  }
}