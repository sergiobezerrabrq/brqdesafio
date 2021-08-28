package com.brq.aviaoms.service;

import com.brq.aviaoms.json.response.AviaoResponse;

import java.util.List;

public interface AviaoService {

    List<AviaoResponse> buscarTodosAvioes();
}
