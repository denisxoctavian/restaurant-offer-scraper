package com.restaurant.scraper.repository;

import com.restaurant.scraper.model.Menu;
import com.restaurant.scraper.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findMenuById(int id);

    List<Menu> findAll();

    @Query(
            value = "SELECT m.* FROM menu m INNER JOIN restaurant r ON m.restaurant_id = r.id WHERE r.id = ?1",
            nativeQuery = true
    )
    public List<Menu> findMenusByRestaurant(int id);

    @Query(
            value ="SELECT * FROM menu where scrap_date = ?1",
            nativeQuery = true
    )
    public List<Menu> findMenusByDate(Date date);

}
