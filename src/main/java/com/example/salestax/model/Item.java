package com.example.salestax.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private final String name;
    private final boolean isImported;
    private final boolean isExempt;
    private final int quantity;
    private final double price;

    public Item(String name, boolean isImported, boolean isExempt, int quantity, double price) {
        this.name = name;
        this.isImported = isImported;
        this.isExempt = isExempt;
        this.quantity = quantity;
        this.price = price;
    }
}
