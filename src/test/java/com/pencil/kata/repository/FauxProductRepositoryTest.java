package com.pencil.kata.repository;

import com.pencil.kata.domain.Product;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FauxProductRepositoryTest {

    private FauxProductRepository fauxProductRepository;

    @Before
    public void setUp() throws Exception {
        fauxProductRepository = new FauxProductRepository();
    }

    @Test
    public void returnsAProduct() {
        String id = "prod2";
        Product foundProduct = fauxProductRepository.getProductById(id);

        assertThat(foundProduct.getId()).isEqualTo(id);
    }
}
