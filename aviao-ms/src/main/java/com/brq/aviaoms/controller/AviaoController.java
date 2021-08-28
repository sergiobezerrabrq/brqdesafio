package com.brq.aviaoms.controller;

import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.service.AviaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/avioes")
@RequiredArgsConstructor
public class AviaoController {

    private final AviaoService aviaoService;

    /**
     * Endpoint responsável por buscar todos os aviões
     * @return Lista de Aviões
     */
    @GetMapping
    public ResponseEntity<List<AviaoResponse>> buscarTodosAvioes(){
        return ResponseEntity.ok().body(aviaoService.buscarTodosAvioes());
    }
}
