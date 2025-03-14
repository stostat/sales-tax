package com.example.salestax.service;

import com.example.salestax.model.Item;
import com.example.salestax.model.Tax;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculator {

    private static final double BASIC_TAX_RATE = 0.10;
    private static final double IMPORT_TAX_RATE = 0.05;

    private final Tax tax;

    public TaxCalculator() {
        this.tax = new Tax(BASIC_TAX_RATE, IMPORT_TAX_RATE);
    }

    public double calculateTax(Item item) {
        return tax.calculateTax(item);
    }

    public double calculateFinalPrice(Item item) {
        double tax = calculateTax(item);
        return (item.getPrice() + tax) * item.getQuantity();
    }

}
