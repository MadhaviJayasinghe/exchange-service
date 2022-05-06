package com.dexlk.exchangeservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;

@DynamoDBTable(tableName = "exchange_rates")
public class ExchangeRate implements Serializable {
    private String id;
    private String exchangeFrom;
    private String exchangeTo;
    private Number exchangeAmount;

    public ExchangeRate() {
    }

    public ExchangeRate(String id, String exchangeFrom, String exchangeTo, Number exchangeAmount) {
        this.id = id;
        this.exchangeFrom = exchangeFrom;
        this.exchangeTo = exchangeTo;
        this.exchangeAmount = exchangeAmount;
    }

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BodyTypeConverter.class)
    public Number getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(Number exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    @DynamoDBAttribute
    public String getExchangeFrom() {
        return exchangeFrom;
    }

    public void setExchangeFrom(String exchangeFrom) {
        this.exchangeFrom = exchangeFrom;
    }

    @DynamoDBAttribute
    public String getExchangeTo() {
        return exchangeTo;
    }

    public void setExchangeTo(String exchangeTo) {
        this.exchangeTo = exchangeTo;
    }

    static public class BodyTypeConverter implements DynamoDBTypeConverter<String, Number> {

        @Override
        public String convert(Number object) {
            Number itemDimensions = (Number) object;

            // Convert the object to a DynamoDB json string
            String json = String.valueOf(itemDimensions);

            return json;        }

        @Override
        public Number unconvert(String s) {
            Number num = Integer.parseInt(s);
            return num;
        }

    }
}