package com.group4.group4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.group4.dtos.pokeapi.PokemonDTO;
import com.group4.group4.services.pokeapi.PokeApiService;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    PokeApiService pokeApiService;

    @GetMapping
    public ResponseEntity<PokemonDTO> getPokemon() {
        // gerar uma id pegando um numero de 1 até o numero máximo de pokemons
        // mantemos as coisas simples com pokemons da primeira geração apenas
        int maxPokemonId = 151;
        int randomPokemonId = (int) (Math.random() * maxPokemonId) + 1;
        return ResponseEntity.ok(pokeApiService.getPokemon(randomPokemonId + ""));
    }
}
