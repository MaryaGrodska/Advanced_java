package com.advanced_java.HrodskaM.model;

import java.math.BigDecimal;

public class ExchangeRate {
    private String ccy;        // Код валюти
    private String base_ccy;   // Базова валюта
    private BigDecimal buy;    // Курс купівлі
    private BigDecimal sale;   // Курс продажу

    public ExchangeRate(String ccy, String base_ccy, BigDecimal buy, BigDecimal sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    // Переозначення toString для зручного відображення даних
    @Override
    public String toString() {
        return "ExchangeRate{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
