package com.lmg.assembleia_api.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class VotoRequest {

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotNull(message = "Opção é obrigatório")
    private Boolean escolha;

}
