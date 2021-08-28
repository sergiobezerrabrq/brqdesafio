package com.brq.aviaoms.controller;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.service.AviaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/avioes/v1")
@RequiredArgsConstructor
@Api(value = "Aviões")
@Slf4j
public class AviaoController {

    private final AviaoService aviaoService;

    /**
     * Endpoint responsável por buscar todos os aviões
     * @return Lista de Aviões
     */
    @GetMapping
    @ApiOperation(value = "All Aviões")
    public ResponseEntity<List<AviaoResponse>> buscarTodosAvioes(){
        log.info("Endpoint GET buscarTodosAvioes()");
        return ResponseEntity.ok().body(aviaoService.buscarTodosAvioes());
    }

    /**
     * Endpoint responsável por buscar avião por Id.
     * @param id
     * @return Avião
     */
   @GetMapping(value = "/{id}")
   @ApiOperation(value = "Aviões by Id")
   public ResponseEntity<AviaoResponse> buscarAviaoPorId(@PathVariable @NotBlank UUID id){
       log.info("Endpoint GET buscarAviaoPorId(...)");
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
   @ApiOperation(value = "Aviões by Filter")
   public ResponseEntity<List<AviaoResponse>> buscarAviaoPorFiltro(@RequestParam (name = "modelo", required = false) String modelo,
                                                                   @RequestParam (name = "fabricante", required = false) String fabricante,
                                                                   @RequestParam (name = "empresa", required = false) String empresa,
                                                                   @RequestParam (name = "motor", required = false) String motor,
                                                                   @RequestParam (name = "qtd_passageiros", required = false) Integer qtdPassageiros,
                                                                   @RequestParam (name = "qtd_portas_saida", required = false) Integer qtdPortasSaida,
                                                                   @RequestParam (name = "altitude_maxima", required = false) Double altitudeMaxima,
                                                                   @RequestParam (name = "velocidade_maxima", required = false) Double velocidadeMaxima,
                                                                   @RequestParam (name = "capacidade_maxima_voo", required = false) Double capacidadeMaximaVoo){
       log.info("Endpoint GET buscarAviaoPorFiltro(...)");
       return ResponseEntity.ok().body(aviaoService.buscarAviaoPorFiltro(modelo, fabricante, empresa, motor, qtdPassageiros, qtdPortasSaida, altitudeMaxima, velocidadeMaxima, capacidadeMaximaVoo));
    }

    /**
     * Endpoint responsável por cadastrar avião.
     * @param aviaoRequest
     * @param bindingResult
     * @return Avião
     */
    @PostMapping
    @ApiOperation(value = "Create Aviões")
    public ResponseEntity<AviaoResponse> postAviao(@RequestBody @Valid AviaoRequest aviaoRequest,
                                                                      BindingResult bindingResult){
       log.info("Endpoint POST postAviao(...)");
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
    @ApiOperation(value = "Update Aviões")
    public ResponseEntity<AviaoResponse> putAviao(@PathVariable @NotBlank UUID id,
                                                  @RequestBody @Valid AviaoRequest aviaoRequest,
                                                  BindingResult bindingResult){
        log.info("Endpoint PUT putAviao(...)");
        return aviaoService.putAviao(aviaoRequest, bindingResult, id);
    }

    /**
     * Endpoint responsável por deletar avião.
     * @param id
     * @return HttpStatus.Ok = 200
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete Aviões")
    public ResponseEntity<AviaoResponse> deleteAviao(@PathVariable @NotBlank UUID id){
        log.info("Endpoint DELETE deleteAviao(...)");
        return aviaoService.deleteAviao(id);
    }
}
