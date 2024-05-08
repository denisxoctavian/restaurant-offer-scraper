package com.restaurant.scraper.service;

import com.restaurant.scraper.model.Menu;
import com.restaurant.scraper.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MenuService {


    private final MenuRepository menuRepository;

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findMenuById(int id){
        return menuRepository.findMenuById(id);
    }

    public List<Menu> findMenusByRestaurant(int id){
        return menuRepository.findMenusByRestaurant(id);
    }

    public List<Menu> findMenusByDate(Date date){
        return menuRepository.findMenusByDate(date);
    }
}
