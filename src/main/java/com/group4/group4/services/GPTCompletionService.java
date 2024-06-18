package com.group4.group4.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.group4.group4.dtos.Completion;
import com.group4.group4.dtos.GPTRequest;
import com.group4.group4.exceptions.InvalidGPTResponseException;



@Service
public class GPTCompletionService {
    @Value("${openai.api.url}")
    private String apiUrl;
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    public Completion getCompletion(GPTRequest request) {

        ResponseEntity<Completion> completionResponse = restTemplate.postForEntity(
            apiUrl, 
            request, 
            Completion.class
        );
        
        if(completionResponse == null || !completionResponse.hasBody()) {
            throw new InvalidGPTResponseException("GPTRequest postForEntity retornou completionResponse nula ou sem corpo!");
        }

        Completion completionBody = completionResponse.getBody();
        
        if(completionBody == null) {
            throw new InvalidGPTResponseException("GPTRequest postForEntity retornou completion com corpo nulo!");
        }

        return completionBody;
    }   







}
