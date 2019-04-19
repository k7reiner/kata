package com.pencil.kata.domain;


import lombok.Getter;
import java.time.LocalDate;

@Getter
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

}
