package com.pencil.kata.repository;

import com.pencil.kata.domain.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FauxProductRepository {

    public FauxProductRepository() {
    }

    public Product getProductById(String searchId) {
        List<Product> fauxProductDb = new ArrayList();
        fauxProductDb.add(new Product("prod1", "Price dropped within range", 10.00, LocalDate.of(2018, 12, 31), false, 12.00, null));
        fauxProductDb.add(new Product("prod2", "Price same - no red pencil - no red pencil", 20.00, LocalDate.of(2019, 04, 12), false, 20.00, null));
        fauxProductDb.add(new Product("prod3", "Price increased during red pencil sale", 30.00,  LocalDate.of(2018,12, 31), false, 20.00,LocalDate.now().minusDays(5)));
        fauxProductDb.add(new Product("prod4", "Current red pencil sale cancel due to price increase", 40.00,  LocalDate.of(2018, 12, 31), true, 10.00,LocalDate.now().minusDays(5)));
        fauxProductDb.add(new Product("prod5", "Price dropped outside of range", 69.00,  LocalDate.of(2018, 12, 31), false, 100.00,null));
        fauxProductDb.add(new Product("prod6", "Lamp", 40.00,  LocalDate.of(2018, 12, 31), false, 50.00,null));

        return fauxProductDb.stream().filter(product -> product.getId().equals(searchId)).collect(Collectors.toList()).get(0);
    }


}
