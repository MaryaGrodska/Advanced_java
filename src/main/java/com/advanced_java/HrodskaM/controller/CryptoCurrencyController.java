package com.advanced_java.HrodskaM.controller;

import com.advanced_java.HrodskaM.dto.CryptoCurrencyQuote;
import com.advanced_java.HrodskaM.service.CryptoCurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CryptoCurrencyController {
    private final CryptoCurrencyService cryptoCurrencyService;
    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    // Метод для отримання поточного курсу біткоїна
    @GetMapping("/get-bitcoin-rate")
    public ResponseEntity<String> getBitcoinRate() {
        String bitcoinRate = cryptoCurrencyService.getBitcoinRate();  // Get the Bitcoin rate
        return ResponseEntity.ok(bitcoinRate);
    }

    // Метод для пошуку криптовалют
    @GetMapping("/parse-crypto")
    public ResponseEntity<List<CryptoCurrencyQuote>> parseCrypto(@RequestParam String searchQuery) {
        List<CryptoCurrencyQuote> cryptocurrencies = cryptoCurrencyService.getQuotes(searchQuery);
        return ResponseEntity.ok(cryptocurrencies);
    }

}
