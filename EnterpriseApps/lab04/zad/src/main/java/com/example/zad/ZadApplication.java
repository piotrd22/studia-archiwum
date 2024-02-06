package com.example.zad;

import com.example.zad.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZadApplication.class, args);
	}

}
