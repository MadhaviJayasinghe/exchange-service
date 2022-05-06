package com.dexlk.exchangeservice.model;

public class ExchangeWallet {
    private String id;
    private Number exchangeFromAmount;
    private Number exchangeToAmount;
    private String walletAddress;

    public ExchangeWallet() {
    }

    public ExchangeWallet(String id, Number exchangeFromAmount, Number exchangeToAmount, String walletAddress) {
        this.id = id;
        this.exchangeFromAmount = exchangeFromAmount;
        this.exchangeToAmount = exchangeToAmount;
        this.walletAddress = walletAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getExchangeFromAmount() {
        return exchangeFromAmount;
    }

    public void setExchangeFromAmount(Number exchangeFromAmount) {
        this.exchangeFromAmount = exchangeFromAmount;
    }

    public Number getExchangeToAmount() {
        return exchangeToAmount;
    }

    public void setExchangeToAmount(Number exchangeToAmount) {
        this.exchangeToAmount = exchangeToAmount;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}

