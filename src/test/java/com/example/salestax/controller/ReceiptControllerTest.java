package com.example.salestax.controller;

import com.example.salestax.service.ReceiptGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.HttpHeaders;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReceiptControllerTest {
    private TestRestTemplate restTemplate;

    @MockitoBean
    private ReceiptGenerator receiptGenerator;

    @BeforeEach
    void setUp() {
        Mockito.when(receiptGenerator.generateReceipt(Mockito.anyList()))
                .thenReturn("Mocked Receipt Output");
    }

    @Test
    void testGenerateReceiptWithValidInput() {
        String payload = "[{\"name\":\"book\",\"isImported\":false,\"isExempt\":true,\"quantity\":1,\"price\":12.49}]";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        String response = restTemplate.postForObject(
                "/api/receipt",
                request,
                String.class
        );

        assertThat(response).isEqualTo("Mocked Receipt Output");
    }

    @Test
    void testGenerateReceiptWithEmptyInput() {
        String payload = "[]";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        Mockito.when(receiptGenerator.generateReceipt(Collections.emptyList()))
                .thenReturn("No items in the receipt");

        String response = restTemplate.postForObject(
                "/api/receipts/generate",
                request,
                String.class
        );

        assertThat(response).isEqualTo("No items in the receipt");
    }

}