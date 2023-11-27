package com.hacknode.orderservice.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String orderNumber;
   @OneToMany(cascade = CascadeType.ALL)
   private List<OrderLineItems> orderLineItems;
}
