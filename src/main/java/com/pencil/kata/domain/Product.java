package com.pencil.kata.domain;


import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
public class Product {

    private String id;
    private double price;
    private LocalDate dateLastPriceChange;
    private String name;
    private double previousPrice;
    private final LocalDate redPencilStartDate;

    public Product(String id, String name, double price, LocalDate dateLastPriceChange, double previousPrice, LocalDate redPencilStartDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateLastPriceChange = dateLastPriceChange;
        this.previousPrice = previousPrice;
        this.redPencilStartDate = redPencilStartDate;
    }

}
