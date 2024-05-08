package com.restaurant.scraper.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne()
    @JoinColumn(name="restaurantId",referencedColumnName = "id")
    private Restaurant restaurantId;

    private byte[] picture;

    private double price;

    private Date scrapDate;

    public Menu() {}


    public Menu(String name, Restaurant restaurantId, byte[] picture, double price, Date scrapDate) {
        this.name = name;
        this.restaurantId = restaurantId;
        this.picture = picture;
        this.price = price;
        this.scrapDate = scrapDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(Date scrapDate) {
        this.scrapDate = scrapDate;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", picture=" + Arrays.toString(picture) +
                ", price=" + price +
                ", scrapDate=" + scrapDate +
                '}';
    }
}
