package com.group4.group4.dtos.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Species(FlavorTextEntry[] flavor_text_entries) {

}
