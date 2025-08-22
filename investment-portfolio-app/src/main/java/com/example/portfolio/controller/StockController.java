package com.example.portfolio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Value("${alphavantage.apikey}")
    private String apiKey;

    @GetMapping("/{ticker}")
    public ResponseEntity<?> getStockQuote(@PathVariable String ticker) {
        String symbol = ticker.toUpperCase() + ".SA"; // Exemplo: PETR4.SA
        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" +
                symbol + "&apikey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        try {
            String result = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar cotação: " + e.getMessage());
        }
    }
}