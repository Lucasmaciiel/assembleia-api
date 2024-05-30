package com.lmg.assembleia_api.infrastructure.repository;

import com.lmg.assembleia_api.infrastructure.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {
}
