package com.lmg.assembleia_api.infrastructure.repository;

import com.lmg.assembleia_api.infrastructure.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

    Optional<Voto> findByCpfAndPautaId(String cpf, Integer id);

    List<Voto> findByPautaId(Integer pautaId);
}
