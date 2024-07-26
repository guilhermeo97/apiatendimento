package com.atendimentos.api.apiatendimento.api.model.input;

import java.math.BigDecimal;

import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCadastrarServico(
    
    @NotBlank(message = "Campo obrigatório")
    String nomeServico,
    
    @Valid
    Fornecedor fornecedor,

    @NotNull
    BigDecimal custoServico
) {
    
}
