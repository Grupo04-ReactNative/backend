package com.group4.group4.dtos.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Sprites(
    String back_default,
    String back_female,
    String back_shiny,
    String back_shiny_female,
    String front_default,
    String front_female,
    String front_shiny,
    String front_shiny_female
    ) {

        public String[] toArray() {
            return new String[] {
                this.back_default(),
                this.back_female(),
                this.back_shiny(),
                this.back_shiny_female(),
                this.front_default(),
                this.front_female(),
                this.front_shiny(),
                this.front_shiny_female()
            };
        }
        
}

