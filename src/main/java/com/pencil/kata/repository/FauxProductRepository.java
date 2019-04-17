package com.pencil.kata.repository;

import com.pencil.kata.domain.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FauxProductRepository {

    List<Product> fauxProductDb = new ArrayList();

    public FauxProductRepository() {
        this.fauxProductDb.add(new Product("patio1", "Last price higher", 10.00, LocalDate.of(2018, 12, 31), false, 20.00, null));
        this.fauxProductDb.add(new Product("patio2", "Price same - no red pencil - no red pencil", 20.00, LocalDate.of(2019, 04, 12), false, 20.00, null));
        this.fauxProductDb.add(new Product("patio3", "Table", 30.00,  LocalDate.of(2018,12, 31), false, 40.00,null));
        this.fauxProductDb.add(new Product("patio4", "Lamp", 40.00,  LocalDate.of(2018, 12, 31), false, 10.00,null));
    }

    public Product getProductById(String searchId) {
        return fauxProductDb.stream().filter(product -> product.getId() == searchId).collect(Collectors.toList()).get(0);

    }


}
