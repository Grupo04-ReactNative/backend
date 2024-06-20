package com.group4.group4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.group4.dtos.FrasePersonalidadeDTO;
import com.group4.group4.dtos.gpt.GPTRequestBody;
import com.group4.group4.services.FrasePersonalidadeService;

@RestController
@RequestMapping("/personalidade")
public class FrasePersonalidadeController {

    @Autowired
    FrasePersonalidadeService service;

    @PostMapping
    public ResponseEntity<FrasePersonalidadeDTO> gerarFrase(@RequestBody GPTRequestBody request) {
        return ResponseEntity.ok(new FrasePersonalidadeDTO(service.gerarFrase(request)));
    }
}
