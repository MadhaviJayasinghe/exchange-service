package com.dexlk.exchangeservice.controller;

import com.dexlk.exchangeservice.VO.ResponseTemplateVO;
import com.dexlk.exchangeservice.VO.ValidationResponseTemplateVO;
import com.dexlk.exchangeservice.model.ExchangeWallet;
import com.dexlk.exchangeservice.repository.AuthRepository;
import com.dexlk.exchangeservice.repository.ExchangeWalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class ExchangeWalletController {
    @Autowired
    private ExchangeWalletRepository exchangeWalletRepository;
    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/getWallet/{walletAddress}")
    public ResponseTemplateVO getWallet(@PathVariable("walletAddress") String walletAddress) {
        log.info("Inside getWalletBalance of ExchangeWalletController");
        return exchangeWalletRepository.getWallet(walletAddress);
    }

    @PostMapping("/exchange/{walletAddress}")
    public void exchangeFund(@PathVariable("walletAddress") String walletAddress, @RequestBody ExchangeWallet exchangeWallet, @RequestHeader Map<String, String> headers) {
        log.info("Inside exchangeFund of ExchangeWalletController");
        headers.forEach((key, value) -> {
            if (key.equals("authorization")) {
                String token = value.substring(7);
                ValidationResponseTemplateVO a = authRepository.validate(token);
                log.info(String.format("Header '%s' = %s", key, token));
            }
        });
        exchangeWalletRepository.exchangeFund(walletAddress, exchangeWallet);
    }
}
