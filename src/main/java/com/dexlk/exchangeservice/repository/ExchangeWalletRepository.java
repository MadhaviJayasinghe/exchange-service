package com.dexlk.exchangeservice.repository;

import com.dexlk.exchangeservice.VO.ResponseTemplateVO;
import com.dexlk.exchangeservice.VO.Wallet;
import com.dexlk.exchangeservice.controller.ExchangeRateController;
import com.dexlk.exchangeservice.model.ExchangeRate;
import com.dexlk.exchangeservice.model.ExchangeWallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${store-fund-url}")
    private String storeFundUrl;

    @Value("${get-wallet-url}")
    private String getWalletUrl;

    public ResponseTemplateVO getWallet(String walletAddress) {
        log.info("Inside getWalletBalance of ExchangeWalletService");
        ResponseTemplateVO vo = new ResponseTemplateVO();

        Wallet wallet =
                restTemplate.getForObject(getWalletUrl + walletAddress
                        , Wallet.class);

        vo.setWallet(wallet);

        return vo;
    }

    public void exchangeFund(String walletAddress, ExchangeWallet exchangeWallet) {
        log.info("Inside saveExchangeWallet of ExchangeWalletService");
        ResponseTemplateVO wallet = getWallet(walletAddress);
        Wallet walletDetails = wallet.getWallet();
        ResponseEntity<ExchangeRate> exchangeRate = exchangeRateController.findRate(exchangeWallet.getConvertFrom(), exchangeWallet.getConvertTo());
        log.info("Inside saveExchangeWallet of ExchangeWalletService {}", exchangeWallet.getAmount());

        Number walletBalance = walletDetails.getUsdBalance();
        if (walletBalance.intValue() > exchangeWallet.getAmount().intValue()) {
            Number fund = exchangeRate.getBody().getExchangeAmount().intValue() * exchangeWallet.getAmount().intValue();
            Number usdValue = wallet.getWallet().getUsdBalance().intValue() - exchangeWallet.getAmount().intValue();
            Number bitcoinVale = wallet.getWallet().getBitcoinBalance().intValue() + fund.intValue();

            if (walletDetails.getUsdBalance() != null) {
                walletDetails.setUsdBalance(usdValue);
            }

            if (walletDetails.getBitcoinBalance() != null) {
                walletDetails.setBitcoinBalance(bitcoinVale);
            }

            restTemplate.postForObject(storeFundUrl + walletAddress, walletDetails, Wallet.class);
        } else {
            log.info("Inside saveExchangeWallet of ExchangeWalletService>>>>>>>>2");
        }
    }
}