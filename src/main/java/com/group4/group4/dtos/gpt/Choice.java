package com.group4.group4.dtos.gpt;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Choice(String finishReason, Message message) {}
