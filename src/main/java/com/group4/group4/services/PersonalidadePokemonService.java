package com.group4.group4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.group4.dtos.PersonalidadePokemonDTO;
import com.group4.group4.dtos.gpt.Completion;
import com.group4.group4.dtos.gpt.GPTRequest;
import com.group4.group4.dtos.gpt.GPTRequestBody;
import com.group4.group4.dtos.pokeapi.PokemonDTO;
import com.group4.group4.services.gpt.GPTCompletionService;
import com.group4.group4.services.gpt.GPTRequestService;
import com.group4.group4.services.pokeapi.PokeApiService;

@Service
public class PersonalidadePokemonService {

    @Autowired
    PokeApiService pokeApiService;

    @Autowired
    private GPTRequestService requestService;

    @Autowired
    private GPTCompletionService completionService;

    public PersonalidadePokemonDTO getPersonalidadePokemon(String id) {

        PokemonDTO pokemon = pokeApiService.getPokemon(id);
        // cria o request body para a mensagem de personalidade personalizada
        GPTRequestBody requestBody = new GPTRequestBody(
            pokemon.name() + " " + pokemon.speciesFlavorText(), 
            0
        );

        // cria o request para o gpt
        GPTRequest gptRequest = requestService.getGPTRequest(requestBody);
        if(gptRequest == null) {
            throw new IllegalArgumentException(
                "Unable to create  GPTRequest from GPTRequestBody userMessagePrompt: "
                + requestBody.getUserMessagePrompt()
            );
        }

        Completion completion = completionService.getCompletion(gptRequest);
        String frase = completion.choices().get(0).message().content().trim();
        
       return new PersonalidadePokemonDTO(
        pokemon.sprites(),
        pokemon.name(),
        frase
       );
    }



}
