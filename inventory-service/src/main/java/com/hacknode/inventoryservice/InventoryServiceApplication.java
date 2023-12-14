package com.hacknode.inventoryservice;

import com.hacknode.inventoryservice.Model.Inventory;
import com.hacknode.inventoryservice.Repositories.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadInventory(InventoryRepository inventoryRepository){
		return args -> {
			Inventory iphone13 = new Inventory();
			iphone13.setSkuCode("Iphone 13");
			iphone13.setQuantity(100);

			Inventory samsungGalaxy = new Inventory();
			samsungGalaxy.setSkuCode("Samsung Galaxy X2");
			samsungGalaxy.setQuantity(2);

			inventoryRepository.save(samsungGalaxy);
			inventoryRepository.save(iphone13);
		};
	}

}
