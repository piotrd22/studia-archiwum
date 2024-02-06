package com.example.zad.config;

import com.example.zad.domain.Restaurant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class DbConfig {
    @Bean
    public List<Restaurant> setupDb() {
        return Collections.synchronizedList(new ArrayList<>());
    }
}
