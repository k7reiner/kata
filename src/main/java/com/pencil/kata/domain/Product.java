package com.pencil.kata.domain;


import java.time.LocalDate;

public class Product {

    private String id;
    private double price;
    private LocalDate dateLastPriceChange;
    private String name;
    private Boolean redPencil;
    private double previousPrice;
    private final LocalDate redPencilStartDate;

    public Product(String id, String name, double price, LocalDate dateLastPriceChange, Boolean redPencil, double previousPrice, LocalDate redPencilStartDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateLastPriceChange = dateLastPriceChange;
        this.redPencil = redPencil;
        this.previousPrice = previousPrice;
        this.redPencilStartDate = redPencilStartDate;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateLastPriceChange() {
        return dateLastPriceChange;
    }

    public Boolean getRedPencil() {
        return redPencil;
    }

    public double getPreviousPrice() {
        return previousPrice;
    }

    public LocalDate getRedPencilStartDate() {
        return redPencilStartDate;
    }
}
