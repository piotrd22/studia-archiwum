package com.example.zad.controller.web;

import com.example.zad.dto.CreateRestaurantRequestDto;
import com.example.zad.dto.RestaurantDto;
import com.example.zad.dto.UpdateRestaurantRequestDto;
import com.example.zad.exception.RestaurantNotFoundException;
import com.example.zad.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/restaurants")
public class WebRestaurantController {
    private final RestaurantService service;

    public WebRestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllRestaurants(Model model) {
        model.addAttribute("allRestaurantsFromDb", service.getAllRestaurants());
        return "restaurant-all";
    }

    @GetMapping("/add")
    public String createRestaurant() {
        return "restaurant-add";
    }

    @PostMapping("/add")
    public String createRestaurant(@ModelAttribute @Valid CreateRestaurantRequestDto dto, Model model) {
        try {
            service.createRestaurant(dto);
            return "redirect:/restaurants";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "restaurant-add";
        }
    }

    @GetMapping("/update/{id}")
    public String updateRestaurant(@PathVariable UUID id, Model model) {
        try {
            RestaurantDto restaurant = service.getRestaurantById(id);
            model.addAttribute("restaurant", restaurant);
            return "restaurant-update";
        } catch (RestaurantNotFoundException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("allRestaurantsFromDb", service.getAllRestaurants());
            return "restaurant-all";
        }
    }

    @PostMapping("/update/{id}")
    public String updateRestaurant(@PathVariable UUID id, @ModelAttribute @Valid UpdateRestaurantRequestDto dto, Model model) {
        try {
            service.updateRestaurant(dto, id);
            model.addAttribute("allRestaurantsFromDb", service.getAllRestaurants());
            return "redirect:/restaurants";
        } catch (RestaurantNotFoundException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "restaurant-update";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteRestaurant(id);
            redirectAttributes.addFlashAttribute("successMessage", "Restaurant with id: %s deleted".formatted(id));
        } catch (RestaurantNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting restaurant: " + ex.getMessage());
        }
        return "redirect:/restaurants";
    }
}
