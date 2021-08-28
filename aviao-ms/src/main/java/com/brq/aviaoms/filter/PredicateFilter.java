package com.brq.aviaoms.filter;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PredicateFilter {

    private String modelo;
    private String fabricante;
    private String empresa;
    private String motor;
    private Integer qtdPassageiros;
    private Integer qtdPortasSaida;
    private Double altitudeMaxima;
    private Double velocidadeMaxima;
    private Double capacidadeMaximaVoo;

    /**
     * Método responsável por criar Predicate
     * @param modelo
     * @param fabricante
     * @param empresa
     * @param motor
     * @param qtdPassageiros
     * @param qtdPortasSaida
     * @param altitudeMaxima
     * @param velocidadeMaxima
     * @param capacidadeMaximaVoo
     * @return Predicate
     */
    public Predicate createPredicate(String modelo, String fabricante, String empresa, String motor,
                                     Integer qtdPassageiros, Integer qtdPortasSaida,
                                     Double altitudeMaxima, Double velocidadeMaxima, Double capacidadeMaximaVoo){

        AviaoQuerydsl aviao = AviaoQuerydsl.aviaoDomain;
        List<Predicate> listPredicate = new ArrayList<>();

        if(StringUtils.isNoneBlank(modelo)){
            listPredicate.add(aviao.modelo.equalsIgnoreCase(modelo));
        }

        if(StringUtils.isNoneBlank(empresa)){
            listPredicate.add(aviao.empresa.equalsIgnoreCase(empresa));
        }

        if(StringUtils.isNoneBlank(fabricante)){
            listPredicate.add(aviao.fabricante.equalsIgnoreCase(fabricante));
        }

        if(StringUtils.isNoneBlank(motor)){
            listPredicate.add(aviao.motor.equalsIgnoreCase(motor));
        }

        if(qtdPassageiros != null){
            listPredicate.add(aviao.qtdPassageiros.eq(qtdPassageiros));
        }

        if(qtdPortasSaida != null){
            listPredicate.add(aviao.qtdPortasSaida.eq(qtdPortasSaida));
        }

        if(altitudeMaxima != null){
            listPredicate.add(aviao.altitudeMaxima.eq(altitudeMaxima));
        }

        if(velocidadeMaxima != null){
            listPredicate.add(aviao.velocidadeMaxima.eq(velocidadeMaxima));
        }

        if(capacidadeMaximaVoo != null){
            listPredicate.add(aviao.capacidadeMaximaVoo.eq(capacidadeMaximaVoo));
        }


        return ExpressionUtils.allOf(listPredicate);

    }


    /**
     * Método responsável por criar PredicateFilter
     * @param modelo
     * @param fabricante
     * @param empresa
     * @param motor
     * @param qtdPassageiros
     * @param qtdPortasSaida
     * @param altitudeMaxima
     * @param velocidadeMaxima
     * @param capacidadeMaximaVoo
     * @return PredicateFilter
     */
    public static PredicateFilter getPredicateFilter(String modelo, String fabricante, String empresa, String motor,
                                                     Integer qtdPassageiros, Integer qtdPortasSaida,
                                                     Double altitudeMaxima, Double velocidadeMaxima, Double capacidadeMaximaVoo){

        return PredicateFilter.builder()
                .modelo(modelo)
                .fabricante(fabricante)
                .empresa(empresa)
                .motor(motor)
                .qtdPassageiros(qtdPassageiros)
                .qtdPortasSaida(qtdPortasSaida)
                .altitudeMaxima(altitudeMaxima)
                .velocidadeMaxima(velocidadeMaxima)
                .capacidadeMaximaVoo(capacidadeMaximaVoo)
                .build();
    }
}
