package com.group4.group4.dtos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Completion(String id, ArrayList<Choice> choices, Integer created) {}
