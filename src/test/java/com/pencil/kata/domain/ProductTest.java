package com.pencil.kata.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product testProduct;
    private String id = "item1b";
    private double price = 10.50;
    private LocalDate dateLastPriceChange = LocalDate.of(2019, 07, 28);
    private String name = "Test Product";
    private Boolean redPencil = false;
    private double previousPrice = 5.00;

    @Test
    void verifyPropertiesForProductWithNoPreviousSaleStartDate() {
        LocalDate redPencilStartDate = null;

        testProduct = new Product(id, name, price, dateLastPriceChange, redPencil, previousPrice, redPencilStartDate);

        assertThat(this.id).isEqualTo(testProduct.getId());
        assertThat(this.price).isEqualTo(testProduct.getPrice());
        assertThat(this.dateLastPriceChange).isEqualTo(testProduct.getDateLastPriceChange());
        assertThat(this.name).isEqualTo(testProduct.getName());
        assertThat(this.redPencil).isEqualTo(testProduct.getRedPencil());
        assertThat(this.previousPrice).isEqualTo(testProduct.getPreviousPrice());
        assertThat(testProduct.getRedPencilStartDate()).isNull();
    }

    @Test
    void verifyPropertiesForProductThatHasPreviousSaleStartDate() {
        LocalDate redPencilStartDate = LocalDate.of(2019, 06, 15);

        testProduct = new Product(id, name, price, dateLastPriceChange, redPencil, previousPrice, redPencilStartDate);

        assertThat(this.id).isEqualTo(testProduct.getId());
        assertThat(this.price).isEqualTo(testProduct.getPrice());
        assertThat(this.dateLastPriceChange).isEqualTo(testProduct.getDateLastPriceChange());
        assertThat(this.name).isEqualTo(testProduct.getName());
        assertThat(this.redPencil).isEqualTo(testProduct.getRedPencil());
        assertThat(this.previousPrice).isEqualTo(testProduct.getPreviousPrice());
        assertThat(testProduct.getRedPencilStartDate()).isNotNull();
    }
}
