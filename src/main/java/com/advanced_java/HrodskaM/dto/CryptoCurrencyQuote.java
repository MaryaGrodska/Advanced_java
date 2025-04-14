package com.advanced_java.HrodskaM.dto;

import java.time.LocalDateTime;

public class CryptoCurrencyQuote {
    private String currency;
    private double price;
    private LocalDateTime timestamp;

    public CryptoCurrencyQuote(String currency, double price, LocalDateTime timestamp) {
        this.currency = currency;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CryptoCurrencyQuote{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
