package com.dexlk.exchangeservice.controller;

import com.dexlk.exchangeservice.VO.ResponseTemplate1VO;
import com.dexlk.exchangeservice.repository.ExchangeWalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchangeWallet")
@Slf4j
public class ExchangeWalletController {
    @Autowired
    private ExchangeWalletRepository exchangeWalletRepository;

    @GetMapping("/getWallet/{walletAddress}")
    public ResponseTemplate1VO getWallet(@PathVariable("walletAddress") String walletAddress) {
        log.info("Inside getWalletBalance of ExchangeWalletController");
        return exchangeWalletRepository.getWallet(walletAddress);
    }

    @PostMapping("/{walletAddress}/{convertFrom}/{convertTo}/{amount}")
    public void exchangeFund(@PathVariable("walletAddress") String walletAddress, @PathVariable("convertFrom") String convertFrom, @PathVariable("convertTo") String convertTo, @PathVariable("amount") Number amount) {
        log.info("Inside exchangeFund of ExchangeWalletController");
        exchangeWalletRepository.exchangeFund(walletAddress, convertFrom, convertTo, amount);
    }
}
