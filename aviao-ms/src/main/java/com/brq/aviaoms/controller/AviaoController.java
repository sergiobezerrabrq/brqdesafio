package com.brq.aviaoms.controller;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.service.AviaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/avioes/v1")
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

    /**
     * Endpoint responsável por buscar avião por filtro.
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
   @GetMapping(value = "/search")
   public ResponseEntity<List<AviaoResponse>> buscarAviaoPorFiltro(@RequestParam (name = "modelo", required = false) String modelo,
                                                                   @RequestParam (name = "fabricante", required = false) String fabricante,
                                                                   @RequestParam (name = "empresa", required = false) String empresa,
                                                                   @RequestParam (name = "motor", required = false) String motor,
                                                                   @RequestParam (name = "qtd_passageiros", required = false) Integer qtdPassageiros,
                                                                   @RequestParam (name = "qtd_portas_saida", required = false) Integer qtdPortasSaida,
                                                                   @RequestParam (name = "altitude_maxima", required = false) Double altitudeMaxima,
                                                                   @RequestParam (name = "velocidade_maxima", required = false) Double velocidadeMaxima,
                                                                   @RequestParam (name = "capacidade_maxima_voo", required = false) Double capacidadeMaximaVoo){

       return ResponseEntity.ok().body(aviaoService.buscarAviaoPorFiltro(modelo, fabricante, empresa, motor, qtdPassageiros, qtdPortasSaida, altitudeMaxima, velocidadeMaxima, capacidadeMaximaVoo));
    }

    /**
     * Endpoint responsável por cadastrar avião.
     * @param aviaoRequest
     * @param bindingResult
     * @return Avião
     */
    @PostMapping
    public ResponseEntity<AviaoResponse> postAviao(@RequestBody @Valid AviaoRequest aviaoRequest,
                                                                      BindingResult bindingResult){
       return aviaoService.postAviao(aviaoRequest, bindingResult);
    }

    /**
     * Endpoint responsável por atualizar avião.
     * @param id
     * @param aviaoRequest
     * @param bindingResult
     * @return Avião
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<AviaoResponse> putAviao(@PathVariable @NotBlank UUID id,
                                                  @RequestBody @Valid AviaoRequest aviaoRequest,
                                                  BindingResult bindingResult){

        return aviaoService.putAviao(aviaoRequest, bindingResult, id);
    }
}
