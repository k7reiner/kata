package com.pencil.kata;

import com.pencil.kata.domain.Product;
import com.pencil.kata.repository.FauxProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class RedPencilDeterminator {

    private FauxProductRepository fauxProductRepository;
    private Product product;

    public RedPencilDeterminator(FauxProductRepository fauxProductRepository) {
        this.fauxProductRepository = fauxProductRepository;
    }

    public Boolean isQualified(String id) {
        this.product = fauxProductRepository.getProductById(id);
        if(isCurrentlyARedPencilSaleItem()) {
            List criteriaChecklist = Arrays.asList(priceHasBeenReduced(), validInPromoPriceReduction(), redPencilWithin30DayMaxPromoLength());
            Boolean foundFailingCriteria = criteriaChecklist.stream().anyMatch(criteria -> criteria.equals(false));
            return !foundFailingCriteria;
        } else {
            return meetsRedPencilCriteria();
        }
    }

    private Boolean meetsRedPencilCriteria() {
        return priceHasBeenReduced()
                && priceStableForLast30Days()
                && priceReductionWithinLimits()
                && previousPromoDoesNotIntersect();
    }

    private Boolean priceHasBeenReduced() {
        return product.getPreviousPrice() > product.getPrice();
    }

    private Boolean priceStableForLast30Days() {
        return product.getDateLastPriceChange().isBefore(LocalDate.now().minusDays(29));
    }

    private Boolean priceReductionWithinLimits() {
        double lowerLimit = .05;
        double upperLimit = .30;
        double priceReduction = (product.getPreviousPrice() - product.getPrice()) / product.getPreviousPrice();
        return priceReduction >= lowerLimit && priceReduction <= upperLimit ? true : false;
    }

    private Boolean redPencilWithin30DayMaxPromoLength() {
        int maxPromoLength = 30;
        LocalDate redPencilStartDate = product.getRedPencilStartDate();
        if(redPencilStartDate != null) {
            return redPencilStartDate.isAfter(LocalDate.now().minusDays(maxPromoLength)) ||
                    redPencilStartDate.isEqual(LocalDate.now().minusDays(maxPromoLength));
        }
        return true;
    }

    private Boolean isCurrentlyARedPencilSaleItem() {
        return product.getRedPencil();
    }

    private Boolean validInPromoPriceReduction() {
        double upperLimit = .30;
        if (product.getRedPencil()) {
            return ((product.getPreviousPrice() - product.getPrice()) / product.getPreviousPrice()) < upperLimit;
        }
        return null;
    }

    private boolean previousPromoDoesNotIntersect() {
        int lastPromoDaysLimit = 30;
        return product.getRedPencilStartDate().isBefore(LocalDate.now().minusDays(lastPromoDaysLimit));
    }
}
