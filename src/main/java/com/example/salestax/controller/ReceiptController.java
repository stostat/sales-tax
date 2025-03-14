package com.example.salestax.controller;

import com.example.salestax.model.Item;
import com.example.salestax.service.ReceiptGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptGenerator receiptGenerator;
    public ReceiptController(ReceiptGenerator receiptGenerator) {
        this.receiptGenerator = receiptGenerator;
    }

    @PostMapping("/api/receipt")
    public String generateReceipt(@RequestBody List<Item> items) {
        return receiptGenerator.generateReceipt(items);
    }

}
