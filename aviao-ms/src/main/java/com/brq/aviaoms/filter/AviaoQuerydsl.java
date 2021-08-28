package com.brq.aviaoms.filter;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.brq.aviaoms.domain.Aviao;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class AviaoQuerydsl extends EntityPathBase<Aviao> {

    private static final long serialVersionUID = 1L;

    public static final AviaoQuerydsl aviaoDomain = new AviaoQuerydsl("aviaoDomain");

    public final StringPath modelo = Expressions.stringPath("modelo");
    public final StringPath fabricante = Expressions.stringPath("fabricante");
    public final StringPath empresa = Expressions.stringPath("empresa");
    public final StringPath motor = Expressions.stringPath("motor");

    public final NumberPath<Integer> qtdPassageiros = Expressions.numberPath(Integer.class, "qtdPassageiros");
    public final NumberPath<Integer> qtdPortasSaida = Expressions.numberPath(Integer.class, "qtdPortasSaida");
    public final NumberPath<Double> altitudeMaxima = Expressions.numberPath(Double.class, "altitudeMaxima");
    public final NumberPath<Double> velocidadeMaxima = Expressions.numberPath(Double.class, "velocidadeMaxima");
    public final NumberPath<Double> capacidadeMaximaVoo = Expressions.numberPath(Double.class, "capacidadeMaximaVoo");

    public AviaoQuerydsl(String value) {
        super(Aviao.class, forVariable(value));
    }

    public AviaoQuerydsl(Path<? extends Aviao> path) {
        super(path.getType(), path.getMetadata());
    }

    public AviaoQuerydsl(PathMetadata metadata) {
        super(Aviao.class, metadata);
    }
}
