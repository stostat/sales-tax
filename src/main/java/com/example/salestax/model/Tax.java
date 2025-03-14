package com.example.salestax.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class Tax {
    private final double basicTaxRate;
    private final double importTaxRate;

    public Tax(double basicTaxRate, double importTaxRate) {
        this.basicTaxRate = basicTaxRate;
        this.importTaxRate = importTaxRate;
    }

    public double calculateTax(Item item) {
        double tax = 0.0;

        if (!item.isExempt()) {
            tax += item.getPrice() * basicTaxRate;
        }

        if (item.isImported()) {
            tax += item.getPrice() * importTaxRate;
        }
        return roundUpToNearestFiveCents(tax);
    }

    private double roundUpToNearestFiveCents(double value) {
        return new BigDecimal(Math.ceil(value * 20) / 20)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
