package com.worldofmassage.spabot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 50, message = "Минимум 3 символа, максимум 50")
    private String title;
    @Size(min = 5, max = 250, message = "Минимум 5 символов, максимум 250")
    private String description;
    @Min(value = 100, message = "Минимум 100 рублей")
    @Max(value = 20000, message = "Максимум 20000 рублей")
    private int price;
    @Min(value = 15, message = "Минимум 15 минут")
    @Max(value = 300, message = "Максимум 300 минут")
    private int duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
