package com.example.salestax.service;

import com.example.salestax.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ImportDutyTax implements TaxCalculationStrategy{
    private static final double IMPORT_TAX_RATE = 0.05;

    @Override
    public double calculateTax(Item item) {
        if (item.isImported()) {
            return roundUpToNearestFiveCents(item.getPrice() * IMPORT_TAX_RATE);
        }
        return 0.0;
    }

    private double roundUpToNearestFiveCents(double value) {
        return Math.ceil(value * 20.0) / 20.0; // Round up to nearest 0.05
    }
}
