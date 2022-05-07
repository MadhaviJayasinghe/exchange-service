package com.dexlk.exchangeservice.model;

public class ExchangeWallet {
    private String convertFrom;
    private String convertTo;
    private Number amount;

    public ExchangeWallet() {
    }

    public ExchangeWallet(String convertFrom, String convertTo, Number amount) {
        this.convertFrom = convertFrom;
        this.convertTo = convertTo;
        this.amount = amount;
    }

    public String getConvertFrom() {
        return convertFrom;
    }

    public void setConvertFrom(String convertFrom) {
        this.convertFrom = convertFrom;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }
}

