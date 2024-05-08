package com.restaurant.scraper.controller;

import com.restaurant.scraper.model.Menu;
import com.restaurant.scraper.model.Restaurant;
import com.restaurant.scraper.service.MenuService;
import com.restaurant.scraper.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @Autowired
    private ScrapingService scrapService;

    public MenuController(MenuService service, ScrapingService scrapService) {
        this.service = service;
        this.scrapService = scrapService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Menu>> getAllMenus(){
        List<Menu> menus = service.findAll();
        return new ResponseEntity<>(menus,HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id") int id){
        Menu menu = service.findMenuById(id);
        return  new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/findByRestaurantId/{id}")
    public  ResponseEntity<List<Menu>> getMenusByRestaurant(@PathVariable("id") int id){
        List<Menu> menus= service.findMenusByRestaurant(id);
        return new ResponseEntity<>(menus,HttpStatus.OK);
    }

    @GetMapping("/findByDate")
    public ResponseEntity<List<Menu>> getMenusByDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        List<Menu> menus = service.findMenusByDate(date);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PostMapping("/scrap")
    public ResponseEntity<List<Menu>> scrapMenusFromRestaurants(){
        scrapService.retrieveAndSaveMenus();
        return null;
    }


}
