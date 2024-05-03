package com.restaurant.scraper.service;


import com.restaurant.scraper.model.Restaurant;
import com.restaurant.scraper.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Restaurant findRestaurantById(int id){
        return restaurantRepo.findRestaurantById(id);
    }

    public List<Restaurant> findAll(){
        return restaurantRepo.findAllRestaurants();
    }
}
