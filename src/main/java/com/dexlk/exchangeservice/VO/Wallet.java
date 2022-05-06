package com.dexlk.exchangeservice.VO;

public class Wallet {
    private String id;
    private String walletAddress;
    private Number usdBalance;
    private Number bitcoinBalance;
    private String userId;

    public Wallet() {
    }

    public Wallet(String id, String walletAddress, Number usdBalance, Number bitcoinBalance, String userId) {
        this.id = id;
        this.walletAddress = walletAddress;
        this.usdBalance = usdBalance;
        this.bitcoinBalance = bitcoinBalance;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Number getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(Number usdBalance) {
        this.usdBalance = usdBalance;
    }

    public Number getBitcoinBalance() {
        return bitcoinBalance;
    }

    public void setBitcoinBalance(Number bitcoinBalance) {
        this.bitcoinBalance = bitcoinBalance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
