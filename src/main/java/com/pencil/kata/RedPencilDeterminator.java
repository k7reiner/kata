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

    public Boolean activateRedPencil(Product product) {
        if(isCurrentlyARedPencilSaleItem(product)) {
            return (priceIncreasedDuringPromo(product)
                    || !validInPromoPriceReduction(product))
                    || !redPencilWithin30DayMaxPromoLength(product) ? false : true;
        } else {
            return meetsRedPencilCriteria(product);
        }
    }

    public Boolean meetsRedPencilCriteria(Product product) {
        return priceHasBeenReduced(product)
                && priceStableForLast30Days(product)
                && priceReductionWithinLimits(product)
                && previousPromoDoesNotIntersect(product);
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
        double priceReduction = (product.getPreviousPrice() - product.getPrice()) / product.getPreviousPrice();
        return priceReduction >= lowerLimit && priceReduction <= upperLimit ? true : false;
    }

    public Boolean redPencilWithin30DayMaxPromoLength(Product product) {
        int maxPromoLength = 30;
        LocalDate redPencilStartDate = product.getRedPencilStartDate();
        if(redPencilStartDate != null) {
            return redPencilStartDate.isAfter(LocalDate.now().minusDays(maxPromoLength)) ||
                    redPencilStartDate.isEqual(LocalDate.now().minusDays(maxPromoLength));
        }
        return true;
    }

    public Boolean priceIncreasedDuringPromo(Product product) {
        if(redPencilWithin30DayMaxPromoLength(product)) {
            return !priceHasBeenReduced(product);
        }
        return null;
    }

    public Boolean isCurrentlyARedPencilSaleItem(Product product) {
        return product.getRedPencil();
    }


    public Boolean validInPromoPriceReduction(Product product) {
        double upperLimit = .30;
        if (product.getRedPencil()) {
            return ((product.getPreviousPrice() - product.getPrice()) / product.getPreviousPrice()) < upperLimit;
        }
        return null;
    }

    public boolean previousPromoDoesNotIntersect(Product product) {
        int lastPromoDaysLimit = 30;
        return product.getRedPencilStartDate().isBefore(LocalDate.now().minusDays(lastPromoDaysLimit));
    }
}
