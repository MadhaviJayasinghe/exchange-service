package com.dexlk.exchangeservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.dexlk.exchangeservice.VO.TokenRequest;
import com.dexlk.exchangeservice.VO.ValidationResponse;
import com.dexlk.exchangeservice.VO.ValidationResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class AuthRepository {
    @Autowired
    private DynamoDBMapper mapper;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth-url}")
    private String url;

    public ValidationResponseTemplateVO validate(String token) {
            ValidationResponseTemplateVO vo = new ValidationResponseTemplateVO();

            TokenRequest tokenRequest = new TokenRequest();

            tokenRequest.setToken(token);
            ValidationResponse validationResponse =
                    restTemplate.postForObject(url, tokenRequest, ValidationResponse.class);
            if (validationResponse != null) {
                log.info("valid {}", validationResponse.getResponse());

                if (validationResponse.getResponse().equals("true")) {

                    vo.setValidationResponse(validationResponse);

                    return vo;
                }
            }
            else {
                log.info("Invalid {}", "validationResponse.getResponse()");

            }
//        } catch (Exception e) {
//            log.error("Error " + e.getMessage());
//        }
        return null;
    }
}
