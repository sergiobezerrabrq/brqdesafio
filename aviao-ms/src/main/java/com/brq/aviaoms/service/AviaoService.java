package com.brq.aviaoms.service;

import com.brq.aviaoms.json.response.AviaoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AviaoService {

    List<AviaoResponse> buscarTodosAvioes();

    ResponseEntity<AviaoResponse> buscarAviaoPorId(UUID id);
}
