package com.pencil.kata;

import com.pencil.kata.domain.Product;
import com.pencil.kata.repository.FauxProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RedPencilDeterminatorTest {

    private RedPencilDeterminator redPencilDeterminator;

    @Mock
    private FauxProductRepository fauxProductRepository;

    @Mock
    private Product product;

    private String id = "mockId1";

    @Before
    public void setUp() throws Exception {
        redPencilDeterminator = new RedPencilDeterminator(fauxProductRepository);
        when(fauxProductRepository.getProductById(id)).thenReturn(product);
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(8.00);
        when(product.getDateLastPriceChange()).thenReturn(LocalDate.now().minusDays(30));
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(31));
        when(product.getRedPencil()).thenReturn(false);  //not currently a red pencil promo.
    }

    @Test
    public void idQualifiedReturnsTrueWhenAllCriteriaAreMet() {

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(true);
    }

    @Test
    public void isQualifiedReturnsFalseWhenPreviousPriceIsLowerOrEqual() {
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(20.00);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);

        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(10.00);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    @Test
    public void isQualifiedReturnsFalseIfPriceReductionDuringPromoExceeds30Percent() {
        when(product.getRedPencil()).thenReturn(true);
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(30));
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(5.00);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    @Test
    public void isQualifiedReturnsFalseIfPreviousPriceHasNotBeenStableForAtLeast30Days() {
        when(product.getDateLastPriceChange()).thenReturn(LocalDate.now().minusDays(29));

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    @Test
    public void isQualifiedReturnsFalseIfPriceReductionIsNotBetween5And30Percent() {
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(6.99);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);

        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(9.51);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    @Test
    public void isQualifiedReturnsFalseIfPreviousRedPencilPromoInIntersectsLast30Days() {
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(29));

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    //validate current red pencil promos should remain active

    @Test
    public void returnsTrueForAnExistingRedPromoWhenAllCriteriaAreMet() {
        when(product.getRedPencil()).thenReturn(true);
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(30));

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(true);
    }

    @Test
    public void returnsFalseIfPriceIncreasedDuringPromo() {
        when(product.getRedPencil()).thenReturn(true);
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(30));
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(15.00);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    @Test
    public void returnsFalseIfInPromoPriceReductionExceeds30Percent() {
        when(product.getRedPencil()).thenReturn(true);
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(29));
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(6.90);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }

    @Test
    public void returnsFalseIfCurrentPromoExceeds30DayMaxPromoLength() {
        when(product.getRedPencil()).thenReturn(true);
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(31));
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(9.00);

        assertThat(redPencilDeterminator.isQualified(id)).isEqualTo(false);
    }
}
