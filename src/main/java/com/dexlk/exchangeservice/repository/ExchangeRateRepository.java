package com.dexlk.exchangeservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.dexlk.exchangeservice.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ExchangeRateRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public void saveExchangeRate(ExchangeRate exchangeRate) {
        mapper.save(exchangeRate);
    }

    public ExchangeRate findRate(String convertFrom, String convertTo) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(convertFrom));
        eav.put(":val2", new AttributeValue().withS(convertTo));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression ()
                .withFilterExpression("exchangeFrom = :val1 and exchangeTo = :val2").withExpressionAttributeValues(eav);

        List<ExchangeRate> exchangeRates = mapper.scan(ExchangeRate.class, scanExpression);

        if (!exchangeRates.isEmpty()) {
            return exchangeRates.get(0);
        }
        return null;
    }
}
