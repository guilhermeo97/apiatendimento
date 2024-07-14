package com.atendimentos.api.apiatendimento.api.model.output;

import java.math.BigDecimal;

import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Servico;

public record DtoListaServico(
    Long id,
    String nomeServico,
    Fornecedor fornecedor,
    BigDecimal custoServico) {

    public DtoListaServico(Servico servico){
        this(servico.getId(), servico.getNomeServico(), servico.getFornecedor(), servico.getCustoServico());
    }
}