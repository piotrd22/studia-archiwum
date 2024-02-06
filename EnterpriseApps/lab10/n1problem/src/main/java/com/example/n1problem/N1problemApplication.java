package com.example.n1problem;

import com.example.n1problem.domain.Restaurant;
import com.example.n1problem.service.RestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class N1problemApplication {

	public static void main(String[] args) {
		SpringApplication.run(N1problemApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(RestaurantService restaurantService) {
		return (args) -> {
			restaurantService.fillData();

			restaurantService.learning();
		};
	}
}
