package com.advanced_java.HrodskaM.service;

import com.advanced_java.HrodskaM.dto.CryptoCurrencyQuote;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoCurrencyService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;  // Для парсингу JSON

    public CryptoCurrencyService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // Get current Bitcoin rate in UAH
    public String getBitcoinRate() {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";
        String response = restTemplate.getForObject(url, String.class);
        return extractBitcoinRate(response);
    }

    // Використовуємо Jackson для парсингу JSON
    private String extractBitcoinRate(String response) {
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode bitcoinNode = rootNode.path("bitcoin");
            if (bitcoinNode != null && bitcoinNode.has("usd")) {
                return "Bitcoin rate: " + bitcoinNode.path("usd").asText() + " USDT";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "It was not possible to get rate.";
    }

    // Пошук криптовалюти за назвою

        private final WebClient webClient = WebClient.create("https://api.coingecko.com/api/v3");

    public List<CryptoCurrencyQuote> getQuotes(String name) {
        List<CryptoCurrencyQuote> quotes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // беремо історичні ціни за 30 днів
        var response = webClient.get()
                .uri("/coins/" + name + "/market_chart?vs_currency=usd&days=30&interval=daily")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            // парсимо JSON відповідь
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode pricesNode = rootNode.path("prices");

            // перетворюємо кожен елемент JSON в CryptoCurrencyQuote
            for (JsonNode priceNode : pricesNode) {
                long timestamp = priceNode.get(0).asLong(); // час в мілісекундах
                double price = priceNode.get(1).asDouble(); // ціна

                // конвертуємо мілісекунди в LocalDateTime
                LocalDateTime date = Instant.ofEpochMilli(timestamp).atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();

                // додаємо в список
                quotes.add(new CryptoCurrencyQuote(name, price, date));
            }
        } catch (Exception e) {
            e.printStackTrace();  // обробка помилки, якщо не вдалося розпарсити JSON
        }

        return quotes;
    }
}
