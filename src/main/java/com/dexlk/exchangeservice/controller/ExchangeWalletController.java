package com.dexlk.exchangeservice.controller;

import com.dexlk.exchangeservice.VO.ResponseTemplateVO;
import com.dexlk.exchangeservice.model.ExchangeWallet;
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
    public ResponseTemplateVO getWallet(@PathVariable("walletAddress") String walletAddress) {
        log.info("Inside getWalletBalance of ExchangeWalletController");
        return exchangeWalletRepository.getWallet(walletAddress);
    }

    @PostMapping("/{walletAddress}")
    public void exchangeFund(@PathVariable("walletAddress") String walletAddress, @RequestBody ExchangeWallet exchangeWallet) {
        log.info("Inside exchangeFund of ExchangeWalletController");
        exchangeWalletRepository.exchangeFund(walletAddress, exchangeWallet);
    }
}
