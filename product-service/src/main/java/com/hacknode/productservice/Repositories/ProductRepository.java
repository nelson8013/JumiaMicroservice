package com.hacknode.productservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hacknode.productservice.Model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

 

}
