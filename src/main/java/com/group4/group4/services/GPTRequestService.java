package com.group4.group4.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.group4.group4.dtos.GPTRequest;
import com.group4.group4.dtos.GPTRequestBody;
import com.group4.group4.enums.SystemPrompt;



@Service
public class GPTRequestService {

    @Value("${openai.model}")
    private String model;
    @Autowired
    private SystemPromptService systemPromptService;
   
    public GPTRequest getGPTRequest(GPTRequestBody requestBody) {
        GPTRequest request = null;
        String userMessagePrompt = requestBody.getUserMessagePrompt();
        int systemPromptNum = requestBody.getSystemPromptNum();
        SystemPrompt systemPrompt = systemPromptService.getSystemPrompt(systemPromptNum);
        String systemPromptMessage = systemPromptService.getMessage(systemPrompt);

        switch(systemPrompt) {
            case Frase:
                return new GPTRequest(model, userMessagePrompt, systemPromptMessage);
        }
        return request;
    }
}

