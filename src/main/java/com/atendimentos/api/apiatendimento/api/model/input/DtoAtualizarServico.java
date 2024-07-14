package com.atendimentos.api.apiatendimento.api.model.input;

import java.math.BigDecimal;

import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;


public record DtoAtualizarServico(
    String nomeServico,
    Fornecedor fornecedor,
    BigDecimal custoServico) {
    
}
