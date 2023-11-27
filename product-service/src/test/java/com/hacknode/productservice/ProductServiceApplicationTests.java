package com.hacknode.productservice;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacknode.productservice.Dtos.Requests.ProductRequest;
import com.hacknode.productservice.Repositories.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@DynamicPropertySource
//	public static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//			dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//
//	/* The IntegrationTests */
//	@Test
//	public void shouldAddProduct() throws Exception {
//		 ProductRequest productRequest = getProductRequest();
//			String productRequestAsString = objectMapper.writeValueAsString(productRequest);
//
//		mockMvc.perform( MockMvcRequestBuilders.post("/api/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(productRequestAsString)).andExpect(status().isCreated());
//
//			assertEquals(1, productRepository.findAll().size());
//
//	}
//
//	@Test
//	public void shouldGetAllProducts() throws Exception {
//
//			mockMvc.perform( MockMvcRequestBuilders.get("/api/product") ).andDo( print() ).andExpect( status().isOk());
//
//			// mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
//			// .contentType(MediaType.APPLICATION_JSON))
//			// .andExpect(status().isOk())
//			// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//			// .andExpect(jsonPath("$").isArray())
//			// .andExpect(jsonPath("$.length()").value(1))
//			// .andExpect(jsonPath("$[0].id").isString())
//			// .andExpect(jsonPath("$[0].name").value("iphone 13"))
//			// .andExpect(jsonPath("$[0].description").value("Na iphone 13 be dis O"))
//			// .andExpect( jsonPath("$[0].price").value(1200));
//
//	}
//
//	private ProductRequest getProductRequest() {
//		return ProductRequest.builder()
//				.name("iphone 13")
//				.description("Na iphone 13 be dis O")
//				.price(BigDecimal.valueOf(1200))
//				.build();
//	}



}
