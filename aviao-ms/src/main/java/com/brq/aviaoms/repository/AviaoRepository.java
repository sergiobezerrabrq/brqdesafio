package com.brq.aviaoms.repository;

import com.brq.aviaoms.domain.Aviao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AviaoRepository extends JpaRepository<Aviao, UUID>, QuerydslPredicateExecutor<Aviao> {
}
