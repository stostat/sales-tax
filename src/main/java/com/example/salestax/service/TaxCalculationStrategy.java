package com.example.salestax.service;

import com.example.salestax.model.Item;

public interface TaxCalculationStrategy {
    double calculateTax(Item item);
}
