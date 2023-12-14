## About JumiaMicroservice

JumiaMicroservce is a Microservice application built with Java SpringBoot. 
Jumia is an online e-commerce platform in Nigeria. 
This is a fun microservice project that is built using the Spring Cloud and  uses third-party tools.

## Features
-- Gateway: The Microservice will have an API Gateway that will receive all requests and channel them to the appropriate service.
-- Communication: I use Kafka and RabbitMQ for A-synchronous communication between services,
-- Security: I'll server the application with  an Authentication Server called Key Clock
-- Secret Storage: I'll use Vault to store secrets.
-- Distributed Tracing: I'll also use Zipkin for distributed tracing.
-- Logging: For logging, I use Elastic Search, LogStash, and Kibana


## Number of Services.

-- Product Service:
   This service would help us create and view products and act as a catalog.
-- Order Service:
   This service would help us order products. Not until it makes a call to the Inventory service to check if the requested product is in stock or not.
-- Inventory Service:
   This service helps us check if a product is in stock or not. 
-- Notification Service:
   This service sends notifications when an order is placed.

All services will communicate with each other synchronously and A-synchronously.  

## Storage

Each service will have its own persistent layer.
- The product service will store products in MongoDB
- The Order Service will store orders in Mysql
- The Inventory Service will also store inventories in Mysql (might change to Postgres).
- The Notifications service is stateless and therefore does not require a DB.

## Service Structure: High Level

Each service will have a controller that will receive the Http request from a client, a service Layer to handle business logic, 
and Rpositoryy layer and a persistent layer. Some services will communicate with message queues.

