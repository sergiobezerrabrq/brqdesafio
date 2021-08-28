package com.brq.aviaoms.controller;

import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.service.AviaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

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

    /**
     * Endpoint responsável por buscar avião por Id.
     * @param id
     * @return Avião
     */
   @GetMapping(value = "/{id}")
    public ResponseEntity<AviaoResponse> buscarAviaoPorId(@PathVariable @NotBlank UUID id){
        return aviaoService.buscarAviaoPorId(id);
   }
}
