package com.atendimentos.api.apiatendimento.api.model.input;

import java.time.OffsetDateTime;

import com.atendimentos.api.apiatendimento.domain.model.Cliente;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Servico;

public record DtoAtualizarAtendimento(
    Fornecedor fornecedor,
    Cliente cliente,
    Servico servico,
    OffsetDateTime dataInicio,
    OffsetDateTime dataFim
){
    
}
