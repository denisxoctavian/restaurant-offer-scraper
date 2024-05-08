package com.restaurant.scraper.service;

import com.restaurant.scraper.model.Menu;
import com.restaurant.scraper.model.Restaurant;
import com.restaurant.scraper.repository.MenuRepository;
import com.restaurant.scraper.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScrapingService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public ScrapingService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public List<Menu> retrieveAndSaveMenus(){

        List<Restaurant> restaurants = restaurantRepository.findAllRestaurants();
        List<Menu> menus = new ArrayList<Menu>();
        for(Restaurant restaurant : restaurants){
            menus.addAll(scrapMenuFromRestaurant(restaurant));
        }

        try{
            menuRepository.saveAll(menus);
            return menus;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Menu> scrapMenuFromRestaurant(Restaurant restaurant){
        String url = restaurant.getLink();
        List<Menu> menus = new ArrayList<Menu>();
        try{
            Document doc = Jsoup.connect(url).get();
            switch (restaurant.getName()){
                case "Papabun":
                    Elements menuElements = doc.select(".single-menu-product");

                    for (Element menu : menuElements) {
                        if(menu != null ){
                            String imageUrl = menu.select(".img-fluid").attr("abs:src");
                            byte[] imageData = downloadImage(imageUrl);
                            String menuTitle = menu.select(".menu-title h4").text();
                            String menuPrice = menu.select(".menu-price span").text();

                            if(!menuPrice.isEmpty() && !menuTitle.isEmpty()){
                                menus.add(new Menu(menuTitle,restaurant,imageData,Double.parseDouble(menuPrice.replaceAll("[^0-9]", "")),new Date()));
                            }
                        }
                    }
                    break;
                case "Autoservire Central":
                    break;
                case "Cantina Sportivilor":
                    break;
                case "La Tuciuri":
                    break;
                default: break;

            }
            return menus;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] downloadImage(String imageUrl) throws IOException {
        try {
            URL url;
            if (!imageUrl.startsWith("http://") && !imageUrl.startsWith("https://")) {
                url = new URL("https://" + imageUrl);
            } else {
                url = new URL(imageUrl);
            }
            if(url.toString().length()>10){
                try (InputStream in = url.openStream()) {
                    return in.readAllBytes();
                }
            }

        } catch (MalformedURLException e) {
            System.err.println("URL-ul is not valid: " + imageUrl);
            throw e;
        }
        return null;
    }

}
