package com.pragma.food_court.domain.model;

import com.pragma.food_court.domain.exception.InvalidNameException;
import com.pragma.food_court.domain.exception.InvalidPriceException;

public class Dish {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
    private Long restaurantId;
    private boolean active;
public Dish() {
    }
    public Dish(String name, int price, String description, String imageUrl, String category, Long restaurantId) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("El nombre del plato no puede estar vacío");
        }
        if (price <= 0) {
            throw new InvalidPriceException("El precio debe ser un número entero positivo y mayor a 0");
        }


        if (restaurantId == null) {
            throw new IllegalArgumentException("El plato debe estar asociado a un restaurante");
        }

        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.restaurantId = restaurantId;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
