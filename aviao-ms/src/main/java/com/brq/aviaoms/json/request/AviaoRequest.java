package com.brq.aviaoms.json.request;

import com.brq.aviaoms.domain.Aviao;
import com.brq.aviaoms.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AviaoRequest {

    @Pattern(regexp = "\\p{L}+", message = Message.MODELO_NOT_ALLOWED)
    @NotBlank(message = Message.MODELO_NOT_FOUND)
    private String modelo;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = Message.FABRICANTE_NOT_ALLOWED)
    @NotBlank(message = Message.FABRICANTE_NOT_FOUND)
    private String fabricante;

    @Pattern(regexp = "\\p{L}+", message = Message.EMPRESA_NOT_ALLOWED)
    @NotBlank(message = Message.EMPRESA_NOT_FOUND)
    private String empresa;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = Message.MODELO_NOT_ALLOWED)
    @NotBlank(message = Message.MODELO_NOT_FOUND)
    private String motor;

    @NotNull(message = Message.QTD_PASSAGEIROS_NOT_ALLOWED)
    @PositiveOrZero(message = Message.QTD_PASSAGEIROS_NOT_FOUND)
    private Integer qtdPassageiros;

    @NotNull(message = Message.QTD_PORTAS_SAIDA_NOT_ALLOWED)
    @PositiveOrZero(message = Message.QTD_PORTAS_SAIDA_NOT_FOUND)
    private Integer qtdPortasSaida;

    @NotNull(message = Message.ALTITUDE_NOT_ALLOWED)
    @PositiveOrZero(message = Message.ALTITUDE_NOT_FOUND)
    private Double altitudeMaxima;

    @NotNull(message = Message.VELOCIDADE_MAXIMA_NOT_ALLOWED)
    @PositiveOrZero(message = Message.VELOCIDADE_MAXIMA_NOT_FOUND)
    private Double velocidadeMaxima;

    @NotNull(message = Message.CAPACIDADE_MAXIMA_VOO_NOT_ALLOWED)
    @PositiveOrZero(message = Message.CAPACIDADE_MAXIMA_VOO_NOT_FOUND)
    private Double capacidadeMaximaVoo;

    /**
     * Método responsável por criar AviaoRequest
     * @return AviaoRequest
     */
    public AviaoRequest createAviaoRequest(){
        return AviaoRequest.builder()
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
}
