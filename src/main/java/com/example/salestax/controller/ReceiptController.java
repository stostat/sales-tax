package com.example.salestax.controller;

import com.example.salestax.model.Item;
import com.example.salestax.service.TaxCalculation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    private TaxCalculation taxCalculationService;
    public ReceiptController(TaxCalculation taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @PostMapping("/generate")
    public String generateReceipt(@RequestBody List<Item> items) {
        StringBuilder receipt = new StringBuilder();
        double totalTaxes = 0.0;
        double totalCost = 0.0;

        for (Item item : items) {
            double tax = taxCalculationService.calculateTax(item);
            double totalItemPrice = taxCalculationService.calculateTotalPrice(item, tax);

            receipt.append(item.getQuantity()).append(" ").append(item.getName())
                    .append(" : ").append(String.format("%.2f", totalItemPrice)).append("\n");

            totalTaxes += tax * item.getQuantity();
            totalCost += totalItemPrice;
        }

        receipt.append("Sales Taxes: ").append(String.format("%.2f", totalTaxes)).append("\n");
        receipt.append("Total: ").append(String.format("%.2f", totalCost));

        return receipt.toString();
    }

}
