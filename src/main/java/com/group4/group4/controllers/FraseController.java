package com.group4.group4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.group4.dtos.FraseDTO;
import com.group4.group4.dtos.GPTRequestBody;
import com.group4.group4.services.FraseService;

@RestController
@RequestMapping("/frases")
public class FraseController {

    @Autowired
    FraseService service;

    @PostMapping
    public ResponseEntity<FraseDTO> validarTag(@RequestBody GPTRequestBody request) {
        return ResponseEntity.ok(new FraseDTO(service.gerarFrase(request)));
    }
}
