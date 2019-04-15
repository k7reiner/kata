package com.pencil.kata;

import com.pencil.kata.domain.Product;
import com.pencil.kata.repository.FauxProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RedPencilDeterminator {

    private FauxProductRepository fauxProductRepository;

    public RedPencilDeterminator(FauxProductRepository fauxProductRepository) {
        this.fauxProductRepository = fauxProductRepository;
    }

    public Boolean priceHasBeenReduced(Product product) {
        return product.getPreviousPrice() > product.getPrice();
    }

    public Boolean priceStableForLast30Days(Product product) {
        return product.getDateLastPriceChange().isBefore(LocalDate.now().minusDays(29));
    }

    public Boolean priceReductionWithinLimits(Product product) {
        double lowerLimit = .05;
        double upperLimit = .30;
        double priceReduction = (product.getPreviousPrice() / product.getPrice())-1;
        return priceReduction >= lowerLimit && priceReduction <= upperLimit ? true : false;
    }
}
