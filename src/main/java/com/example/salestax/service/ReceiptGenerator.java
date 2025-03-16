package com.example.salestax.service;

import com.example.salestax.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptGenerator {
    private final TaxCalculator taxCalculator;

    public ReceiptGenerator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public String generateReceipt(List<Item> items) {
        StringBuilder receiptBuilder = new StringBuilder();
        double totalTaxes = 0.0;
        double totalCost = 0.0;

        for (Item item : items) {
            double taxAmount = taxCalculator.calculateTotalTax(item);
            double finalPrice = taxCalculator.calculateFinalPrice(item);

            receiptBuilder
                    .append(item.getQuantity()).append(" ")
                    .append(item.getName()).append(" : ")
                    .append(String.format("%.2f", finalPrice))
                    .append("\n");

            totalTaxes += taxAmount * item.getQuantity();
            totalCost += finalPrice;
        }

        receiptBuilder
                .append("Sales Taxes: ").append(String.format("%.2f", totalTaxes)).append("\n")
                .append("Total: ").append(String.format("%.2f", totalCost));

        return receiptBuilder.toString();
    }
}
