package com.desafio_votacao.repository;

import com.desafio_votacao.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    // Fetch explícito dos votos usando JPQL
    @Query("SELECT s FROM Sessao s LEFT JOIN FETCH s.votos WHERE s.id = :id")
    Optional<Sessao> findByIdWithVotos(@Param("id") Long id);

}
