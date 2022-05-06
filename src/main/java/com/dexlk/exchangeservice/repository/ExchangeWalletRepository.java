package com.dexlk.exchangeservice.repository;

import com.dexlk.exchangeservice.VO.ResponseTemplate1VO;
import com.dexlk.exchangeservice.VO.Wallet;
import com.dexlk.exchangeservice.controller.ExchangeRateController;
import com.dexlk.exchangeservice.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class ExchangeWalletRepository {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExchangeRateController exchangeRateController;

    public ResponseTemplate1VO getWallet(String walletAddress) {
        log.info("Inside getWalletBalance of ExchangeWalletService");
        ResponseTemplate1VO vo = new ResponseTemplate1VO();

        Wallet wallet =
                restTemplate.getForObject("http://localhost:9001/wallets/address/" + walletAddress
                        , Wallet.class);

        vo.setWallet(wallet);

        return vo;
    }

    public void exchangeFund(String walletAddress, String convertFrom, String covertTo, Number amount) {
        log.info("Inside saveExchangeWallet of ExchangeWalletService");
        ResponseTemplate1VO wallet = getWallet(walletAddress);
        Wallet walletDetails = wallet.getWallet();
        ResponseEntity<ExchangeRate> exchangeRate = exchangeRateController.findRate(convertFrom, covertTo);
        log.info("Inside saveExchangeWallet of ExchangeWalletService {}", exchangeRate.getBody().getExchangeAmount());

        Number walletBalance = walletDetails.getUsdBalance();
        if (walletBalance.intValue() > amount.intValue()) {
            Number fund = exchangeRate.getBody().getExchangeAmount().intValue() * amount.intValue();
            Number usdValue = wallet.getWallet().getUsdBalance().intValue() - amount.intValue();
            Number bitcoinVale = wallet.getWallet().getBitcoinBalance().intValue() + fund.intValue();
//
            if (walletDetails.getUsdBalance() != null) {
                walletDetails.setUsdBalance(usdValue);
            }

            if (walletDetails.getBitcoinBalance() != null) {
                walletDetails.setBitcoinBalance(bitcoinVale);
            }

            restTemplate.postForObject("http://localhost:9001/wallets/" + walletAddress, walletDetails, Wallet.class);
        } else {
            log.info("Inside saveExchangeWallet of ExchangeWalletService>>>>>>>>2");
        }
    }
}