package com.brq.aviaoms.domain;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Aviao")
public class Aviao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "motor")
    private String motor;

    @Column(name = "qtd_passageiros")
    private Integer qtdPassageiros;

    @Column(name = "qtd_portas_saida")
    private Integer qtdPortasSaida;

    @Column(name = "altitude_maxima")
    private Double altitudeMaxima;

    @Column(name = "velocidade_maxima")
    private Double velocidadeMaxima;

    @Column(name = "capacidade_maxima_voo")
    private Double capacidadeMaximaVoo;

    /**
     * Método responsável por criar Aviao
     * @return Aviao
     */
    public Aviao createAviao(){
        return Aviao.builder()
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
     * Método responsável por criar Aviao de AviaoRequest
     * @return Aviao
     */
    public Aviao createAviaoFromAviaoRequest(AviaoRequest aviaoRequest){
        return Aviao.builder()
                .id(this.id)
                .modelo(aviaoRequest.getModelo())
                .fabricante(aviaoRequest.getFabricante())
                .empresa(aviaoRequest.getEmpresa())
                .motor(aviaoRequest.getMotor())
                .qtdPassageiros(aviaoRequest.getQtdPassageiros())
                .qtdPortasSaida(aviaoRequest.getQtdPortasSaida())
                .altitudeMaxima(aviaoRequest.getAltitudeMaxima())
                .velocidadeMaxima(aviaoRequest.getVelocidadeMaxima())
                .capacidadeMaximaVoo(aviaoRequest.getCapacidadeMaximaVoo())
                .build();
    }

    /**
     * Método responsável por criar AviaoResponse de Aviao
     * @return AviaoResponse
     */
    public AviaoResponse createAviaoResponseFromAviao(){
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

}
