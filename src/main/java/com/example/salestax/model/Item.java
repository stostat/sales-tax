package com.example.salestax.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {
    private String name;
    private boolean isImported;
    private boolean isExempt;
    private int quantity;
    private double price;

    public Item(String name, boolean isImported, boolean isExempt, int quantity, double price) {
        this.name = name;
        this.isImported = isImported;
        this.isExempt = isExempt;
        this.quantity = quantity;
        this.price = price;
    }
}
