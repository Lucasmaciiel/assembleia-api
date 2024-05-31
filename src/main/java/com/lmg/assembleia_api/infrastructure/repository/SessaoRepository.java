package com.lmg.assembleia_api.infrastructure.repository;

import com.lmg.assembleia_api.infrastructure.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

    Long countByPautaId(Integer id);

    Optional<Sessao> findByIdAndPautaId(Integer sessaoId, Integer pautaId);
}
