package com.lmg.assembleia_api.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voto")
public class Voto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7632424860009228565L;

    @Id
    @SequenceGenerator(name = "sessao_seq", sequenceName = "sessao_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_seq")
    private Integer id;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotNull(message = "A escolha é obrigatório")
    private Boolean escolha;

    @NotNull(message = "Necessário informar a Pauta")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;
}
