package com.group4.group4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.group4.dtos.Completion;
import com.group4.group4.dtos.GPTRequest;
import com.group4.group4.dtos.GPTRequestBody;

@Service
public class FraseService {
    
    @Autowired
    private GPTRequestService requestService;

    @Autowired
    private GPTCompletionService completionService;

    public String gerarFrase(GPTRequestBody requestBody) {
        int systemPromptNum = requestBody.getSystemPromptNum();
        if(systemPromptNum != 0) {
            throw new IllegalArgumentException(
                "Illegal system prompt in Frase request "
                + systemPromptNum + " should be 0." 
            );
        }

        GPTRequest gptRequest = requestService.getGPTRequest(requestBody);
        if(gptRequest == null) {
            throw new IllegalArgumentException(
                "Unable to create  GPTRequest from GPTRequestBody userMessagePrompt: "
                + requestBody.getUserMessagePrompt()
            );
        }

        Completion completion = completionService.getCompletion(gptRequest);
        String frase = completion.choices().get(0).message().content().trim();

        return frase;
    }

}
