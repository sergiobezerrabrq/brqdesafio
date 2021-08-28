package com.brq.aviaoms.service.Impl;

import com.brq.aviaoms.domain.Aviao;
import com.brq.aviaoms.filter.PredicateFilter;
import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.repository.AviaoRepository;
import com.brq.aviaoms.service.AviaoService;
import com.brq.aviaoms.service.AviaoValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AviaoServiceImpl implements AviaoService {

    private final AviaoRepository aviaoRepository;
    private final AviaoValidationService aviaoValidationService;

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

    /**
     * Service para buscar avião por filtro
     * @param modelo
     * @param fabricante
     * @param empresa
     * @param motor
     * @param qtdPassageiros
     * @param qtdPortasSaida
     * @param altitudeMaxima
     * @param velocidadeMaxima
     * @param capacidadeMaximaVoo
     * @return Avião
     */
    @Override
    @Transactional(readOnly = true)
    public List<AviaoResponse> buscarAviaoPorFiltro(String modelo, String fabricante, String empresa, String motor,
                                                    Integer qtdPassageiros, Integer qtdPortasSaida,
                                                    Double altitudeMaxima, Double velocidadeMaxima, Double capacidadeMaximaVoo) {

        PredicateFilter predicateFilter = PredicateFilter.getPredicateFilter(modelo, fabricante, empresa, motor, qtdPassageiros, qtdPortasSaida, altitudeMaxima, velocidadeMaxima, capacidadeMaximaVoo);
        return StreamSupport.stream(aviaoRepository.findAll(predicateFilter.createPredicate(modelo, fabricante, empresa, motor, qtdPassageiros, qtdPortasSaida, altitudeMaxima, velocidadeMaxima, capacidadeMaximaVoo)).spliterator(), false)
                .collect(Collectors.toList()).stream().map(aviao -> aviao.createAviaoResponseFromAviao()).collect(Collectors.toList());

    }

    /**
     * Service para cadastrar avião
     * @param aviaoRequest
     * @param bindingResult
     * @return Avião
     */
    @Override
    @Transactional
    public ResponseEntity<AviaoResponse> postAviao(AviaoRequest aviaoRequest, BindingResult bindingResult) {

        if(!aviaoValidationService.isAviaoValid(aviaoRequest, bindingResult)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AviaoResponse.createAviaoResponseValidation(HttpStatus.BAD_REQUEST.value(), bindingResult));
        }

        AviaoResponse aviaoResponse = aviaoRepository.save(aviaoRequest.createAviao()).createAviaoResponseFromAviao();
        return ResponseEntity.status(HttpStatus.CREATED).header("id", aviaoResponse.getId().toString()).body(aviaoResponse);
    }
}
