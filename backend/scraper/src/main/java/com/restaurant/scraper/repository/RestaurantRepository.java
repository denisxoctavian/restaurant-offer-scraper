package com.restaurant.scraper.repository;

import com.restaurant.scraper.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findRestaurantById(int id);

    @Query(
            value ="SELECT * FROM  Restaurant",
            nativeQuery = true
    )
    List<Restaurant> findAllRestaurants();

    @Query(
            value="SELECT * FROM Restaurant WHERE location =?1",
            nativeQuery = true
    )
    List<Restaurant> findAllRestaurantsByLocation(String location);
}
