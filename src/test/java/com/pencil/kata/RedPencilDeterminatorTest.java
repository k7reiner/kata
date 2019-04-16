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

    @Before
    public void setUp() throws Exception {
        redPencilDeterminator = new RedPencilDeterminator(fauxProductRepository);
    }

    @Test
    public void priceHasBeenReducedReturnsTrueWhenLastPriceIsHigher() {

        when(product.getPreviousPrice()).thenReturn(20.00);
        when(product.getPrice()).thenReturn(10.00);

        assertThat(redPencilDeterminator.priceHasBeenReduced(product)).isEqualTo(true);
    }

    @Test
    public void priceHasBeenReducedReturnsFalseWhenLastPriceIsLower() {

        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(20.00);

        assertThat(redPencilDeterminator.priceHasBeenReduced(product)).isEqualTo(false);
    }

    @Test
    public void priceHasBeenReducedReturnsFalseWhenLastPriceIsSame() {
        when(product.getPreviousPrice()).thenReturn(20.00);
        when(product.getPrice()).thenReturn(20.00);

        assertThat(redPencilDeterminator.priceHasBeenReduced(product)).isEqualTo(false);
    }

    @Test
    public void previousPriceHasBeenStableForAtLeast30Days() {
        when(product.getDateLastPriceChange()).thenReturn(LocalDate.now().minusDays(30));

        assertThat(redPencilDeterminator.priceStableForLast30Days(product)).isEqualTo(true);

        when(product.getDateLastPriceChange()).thenReturn(LocalDate.now().minusDays(29));

        assertThat(redPencilDeterminator.priceStableForLast30Days(product)).isEqualTo(false);
    }

    @Test
    public void priceReductionIsBetween5And30Percent() {
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(9.50);

        assertThat(redPencilDeterminator.priceReductionWithinLimits(product)).isEqualTo(true);

        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(5.50);

        assertThat(redPencilDeterminator.priceReductionWithinLimits(product)).isEqualTo(false);

        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(15.50);

        assertThat(redPencilDeterminator.priceReductionWithinLimits(product)).isEqualTo(false);
    }

    @Test
    public void verifyRedPencilStartDateDoesNotExceed30DayMaxLength() {
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(31));

        assertThat(redPencilDeterminator.redPencilWithin30DayMaxPromoLength(product)).isEqualTo(false);

        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(30));

        assertThat(redPencilDeterminator.redPencilWithin30DayMaxPromoLength(product)).isEqualTo(true);
    }

    @Test
    public void determinesIfPriceIncreasedDuringPromo() {
        when(product.getDateLastPriceChange()).thenReturn(LocalDate.now().minusDays(15));
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(15.00);
        when(product.getRedPencilStartDate()).thenReturn(LocalDate.now().minusDays(15));

        assertThat(redPencilDeterminator.priceIncreasedDuringPromo(product)).isEqualTo(true);

        when(product.getPreviousPrice()).thenReturn(15.00);
        when(product.getPrice()).thenReturn(10.00);

        assertThat(redPencilDeterminator.priceIncreasedDuringPromo(product)).isEqualTo(false);
    }

    @Test
    public void priceReductionDuringPromoDoesNotExceed30Percent() {
        when(product.getDateLastPriceChange()).thenReturn(LocalDate.now().minusDays(15));
        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(5.00);

        assertThat(redPencilDeterminator.validInPromoPriceReduction(product)).isEqualTo(false);
    }
}
