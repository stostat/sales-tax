package com.example.salestax.service;

import com.example.salestax.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxCalculator {

    private final List<TaxCalculationStrategy> taxStrategies;

    public TaxCalculator(List<TaxCalculationStrategy> taxStrategies) {
        this.taxStrategies = taxStrategies;
    }

    public double calculateTotalTax(Item item) {
        return taxStrategies.stream()
                .mapToDouble(strategy -> strategy.calculateTax(item))
                .sum();
    }

    public double calculateFinalPrice(Item item) {
        return (item.getPrice() + calculateTotalTax(item)) * item.getQuantity();
    }

}
