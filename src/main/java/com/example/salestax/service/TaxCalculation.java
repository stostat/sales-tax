package com.example.salestax.service;

import com.example.salestax.model.Item;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculation {
    private final double BASIC_SALES_TAX = 0.10;
    private final double IMPORT_DUTY = 0.05;

    public double calculateTax(Item item) {
        double tax = 0.0;

        if (!item.isExempt()) {
            tax += item.getPrice() * BASIC_SALES_TAX;  // Basic Sales Tax for non-exempt items
        }

        if (item.isImported()) {
            tax += item.getPrice() * IMPORT_DUTY;  // Import Duty for imported items
        }

        tax = roundUpTaxToNearestFiveCents(tax);

        return tax;
    }

    private double roundUpTaxToNearestFiveCents(double tax) {
        return Math.ceil(tax * 20.0) / 20.0;  // Multiply by 20 and round up to nearest integer, then divide by 20
    }

    public double calculateTotalPrice(Item item, double tax) {
        return (item.getPrice() + tax) * item.getQuantity();
    }
}
