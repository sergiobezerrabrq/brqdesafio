package com.brq.aviaoms.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AviaoResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private UUID id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String modelo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String fabricante;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String empresa;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String motor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer qtdPassageiros;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer qtdPortasSaida;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double altitudeMaxima;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double velocidadeMaxima;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double capacidadeMaximaVoo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status_code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    /**
     * Método responsável por criar AviaoResponse
     * @return AviaoResponse
     */
    public AviaoResponse createAviaoResponse(){
        return AviaoResponse.builder()
                .id(this.id)
                .modelo(this.modelo)
                .fabricante(this.fabricante)
                .empresa(this.empresa)
                .motor(this.motor)
                .qtdPassageiros(this.qtdPassageiros)
                .qtdPortasSaida(this.qtdPortasSaida)
                .altitudeMaxima(this.altitudeMaxima)
                .velocidadeMaxima(this.velocidadeMaxima)
                .capacidadeMaximaVoo(this.capacidadeMaximaVoo)
                .build();
    }

    /**
     * Método responsável por criar AviaoResponse validation
     * @param value
     * @param bindingResult
     * @return AviaoResponse
     */
    public static AviaoResponse createAviaoResponseValidation(Integer value, BindingResult bindingResult) {
        return AviaoResponse.builder()
                .status_code(value)
                .message(bindingResult.getAllErrors().get(0).getDefaultMessage())
                .build();
    }
}
