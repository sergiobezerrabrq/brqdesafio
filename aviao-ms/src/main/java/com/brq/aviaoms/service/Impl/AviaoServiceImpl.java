package com.brq.aviaoms.service.Impl;

import com.brq.aviaoms.domain.Aviao;
import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.repository.AviaoRepository;
import com.brq.aviaoms.service.AviaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AviaoServiceImpl implements AviaoService {

    private final AviaoRepository aviaoRepository;

    /**
     * Service para buscar todos os aviões
     * @return Lista de Aviões
     */
    @Override
    @Transactional(readOnly = true)
    public List<AviaoResponse> buscarTodosAvioes() {
        return aviaoRepository.findAll().stream().map(aviao -> aviao.createAviaoResponseFromAviao()).collect(Collectors.toList());
    }

    /**
     * Service para buscar avião por Id
     * @param id
     * @return Avião
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AviaoResponse> buscarAviaoPorId(UUID id) {
        Optional<Aviao> optional = aviaoRepository.findById(id);
        return optional.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(optional.get().createAviaoResponseFromAviao())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
