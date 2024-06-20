package com.group4.group4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.group4.dtos.PersonalidadePokemonDTO;
import com.group4.group4.services.PersonalidadePokemonService;

@RestController
@RequestMapping("/personalidade-pokemon")
public class PersonalidadePokemonController {

    @Autowired
    PersonalidadePokemonService service;

    @GetMapping
    public ResponseEntity<PersonalidadePokemonDTO> getPersonalidadePokemon() {
        // gerar uma id pegando um numero de 1 até o numero máximo de pokemons
        // mantemos as coisas simples com pokemons da primeira geração apenas
        int maxPokemonId = 151;
        int randomPokemonId = (int) (Math.random() * maxPokemonId) + 1;
        return ResponseEntity.ok(service.getPersonalidadePokemon(randomPokemonId + ""));
    }
}
