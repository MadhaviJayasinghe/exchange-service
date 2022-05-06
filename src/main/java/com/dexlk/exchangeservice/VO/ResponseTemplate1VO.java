package com.dexlk.exchangeservice.VO;

import com.dexlk.exchangeservice.model.ExchangeWallet;

public class ResponseTemplate1VO {
    private Wallet wallet;

    public ResponseTemplate1VO() {
    }

    public ResponseTemplate1VO(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
