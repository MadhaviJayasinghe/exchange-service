package com.dexlk.exchangeservice.VO;

public class ResponseTemplateVO {
    private Wallet wallet;

    public ResponseTemplateVO() {
    }

    public ResponseTemplateVO(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
