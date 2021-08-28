package com.brq.aviaoms.service;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface AviaoService {

    List<AviaoResponse> buscarTodosAvioes();

    ResponseEntity<AviaoResponse> buscarAviaoPorId(UUID id);

    List<AviaoResponse> buscarAviaoPorFiltro(String modelo, String fabricante, String empresa, String motor, Integer qtdPassageiros, Integer qtdPortasSaida, Double altitudeMaxima, Double velocidadeMaxima, Double capacidadeMaximaVoo);

    ResponseEntity<AviaoResponse> postAviao(AviaoRequest aviaoRequest, BindingResult bindingResult);
}
