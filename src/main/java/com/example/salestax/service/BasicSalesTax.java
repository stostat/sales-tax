package com.example.salestax.service;

import com.example.salestax.model.Item;
import org.springframework.stereotype.Component;

@Component
public class BasicSalesTax implements TaxCalculationStrategy{
    private static final double BASIC_TAX_RATE = 0.10;

    @Override
    public double calculateTax(Item item) {
        // Only apply basic tax to non-exempt items
        if (!item.isExempt()) {
            return roundUpToNearestFiveCents(item.getPrice() * BASIC_TAX_RATE);
        }
        return 0.0;
    }

    private double roundUpToNearestFiveCents(double value) {
        return Math.ceil(value * 20.0) / 20.0; // Round up to nearest 0.05
    }
}
