package com.group4.group4.services.pokeapi;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.group4.group4.dtos.pokeapi.FlavorTextEntry;
import com.group4.group4.dtos.pokeapi.Pokemon;
import com.group4.group4.dtos.pokeapi.PokemonDTO;
import com.group4.group4.dtos.pokeapi.Species;
import com.group4.group4.exceptions.InvalidPokeApiResponseException;

@Service
public class PokeApiService {

    @Value("${pokeapi.url}")
    private String apiUrl;

    @Qualifier("pokeApiRestTemplate")
    @Autowired
    RestTemplate restTemplate;

    public PokemonDTO getPokemon(String idOrName) {
        
        ResponseEntity<Pokemon> response = restTemplate.getForEntity(
            apiUrl + idOrName + "/", Pokemon.class
            );
        if(response == null || !response.hasBody()) {
            throw new InvalidPokeApiResponseException("PokeApi retornou uma resposta nula ou sem corpo para o pokemon!!");
        }
        Pokemon pokemon = response.getBody();
        if(pokemon == null) {
            throw new InvalidPokeApiResponseException("PokeApi retornou um pokemon nulo!!");
        }
        if(pokemon.sprites() == null) {
            throw new InvalidPokeApiResponseException("PokeApi retornou um pokemon com sprites nulos!!");
        }
        if(pokemon.species() == null) {
            throw new InvalidPokeApiResponseException("PokeApi retornou um pokemon com especie nula!!");
        }

        Species species = getSpecies(pokemon.species().url());
        
        String flavorTextEscolhido = processFlavorTextEntries(species.flavor_text_entries());

        if(flavorTextEscolhido == "") {
            throw new InvalidPokeApiResponseException("PokeApiService falhamos em processar flavor_text_entries!");
        }

        PokemonDTO dto = new PokemonDTO(
            pokemon.sprites().toArray(), 
            pokemon.species().name(), 
            flavorTextEscolhido
        );
        return dto;
    }

    public Species getSpecies(String url) {

        ResponseEntity<Species> response = restTemplate.getForEntity(url, Species.class);
        if(response == null || !response.hasBody()) {
            throw new InvalidPokeApiResponseException("PokeApi retornou uma resposta nula ou sem corpo para a espécie do pokemon!");
        }
        Species species = response.getBody();
        if(species == null) {
            throw new InvalidPokeApiResponseException("PokeApi retornou um pokemon nulo!!");

        }

        return species;
    }
    
    private String processFlavorTextEntries(FlavorTextEntry[] entries) {
        String flavorTextEscolhido = "";
        Set<String> uniqueFlavorTexts = new HashSet<>();
        StringBuilder flavorTextBuilder = new StringBuilder();

        for (FlavorTextEntry entry : entries) {
            if (entry.language().name().equals("en")) {
                String cleanedText = entry.flavor_text().replace("\n", " ").replace("\f", " ");
                if (!uniqueFlavorTexts.contains(cleanedText)) {
                    uniqueFlavorTexts.add(cleanedText);
                    flavorTextBuilder.append(" ").append(cleanedText);
                }
            }
        }

        flavorTextEscolhido = flavorTextBuilder.toString().trim();
        return flavorTextEscolhido;
    } 
}
