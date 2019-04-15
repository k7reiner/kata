package com.pencil.kata;

import com.pencil.kata.domain.Product;
import com.pencil.kata.repository.FauxProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
    public void priceDidNotIncreaseReturnsTrueWhenLastPriceIsHigher() {

        when(product.getPreviousPrice()).thenReturn(20.00);
        when(product.getPrice()).thenReturn(10.00);

        assertThat(redPencilDeterminator.priceHasBeenReduced(product)).isEqualTo(true);

    }

    @Test
    public void priceDidNotIncreaseReturnsFalseWhenLastPriceIsLower() {

        when(product.getPreviousPrice()).thenReturn(10.00);
        when(product.getPrice()).thenReturn(20.00);

        assertThat(redPencilDeterminator.priceHasBeenReduced(product)).isEqualTo(false);

    }

    @Test
    public void priceDidNotIncreaseReturnsFalseWhenLastPriceIsSame() {
        when(product.getPreviousPrice()).thenReturn(20.00);
        when(product.getPrice()).thenReturn(20.00);

        assertThat(redPencilDeterminator.priceHasBeenReduced(product)).isEqualTo(false);
    }

}
