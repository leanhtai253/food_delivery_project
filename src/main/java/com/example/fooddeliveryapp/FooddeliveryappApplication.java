package com.example.fooddeliveryapp;

import com.example.fooddeliveryapp.uploads.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class FooddeliveryappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooddeliveryappApplication.class, args);
	}

}
