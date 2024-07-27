package com.atendimentos.api.apiatendimento.api.model.input;

import java.time.OffsetDateTime;

import com.atendimentos.api.apiatendimento.domain.model.Cliente;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Servico;

import jakarta.validation.constraints.NotNull;

public record DtoCadastrarAtendimento(
    
    Fornecedor fornecedor,
    Cliente cliente,
    Servico servico,
    
    @NotNull(message = "Campo Obrigatório")
    OffsetDateTime dataInicio,
    
    @NotNull(message = "Campo Obrigatório")
    OffsetDateTime dataFim
){
    
}
