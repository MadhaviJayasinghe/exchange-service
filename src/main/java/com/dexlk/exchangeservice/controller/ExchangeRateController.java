package com.dexlk.exchangeservice.controller;

import com.dexlk.exchangeservice.model.ExchangeRate;
import com.dexlk.exchangeservice.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
@Slf4j
public class ExchangeRateController {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @PostMapping
    public void saveExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        exchangeRateRepository.saveExchangeRate(exchangeRate);
    }

    @GetMapping("/getRate/{convertFrom}/{convertTo}")
    public ResponseEntity<ExchangeRate> findRate(@PathVariable("convertFrom") String convertFrom, @PathVariable("convertTo") String convertTo) {
        log.info("Inside findRate method of ExchangeWalletController");
        ExchangeRate exchangeRate = exchangeRateRepository.findRate(convertFrom, convertTo);

        return new ResponseEntity<ExchangeRate>(exchangeRate, HttpStatus.OK);
    }
}
