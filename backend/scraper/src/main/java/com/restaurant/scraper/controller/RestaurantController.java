package com.restaurant.scraper.controller;


import com.restaurant.scraper.model.Restaurant;
import com.restaurant.scraper.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = service.findAll();
        return  new ResponseEntity<>(restaurants,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") int id){
        Restaurant restaurant = service.findRestaurantById(id);
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
