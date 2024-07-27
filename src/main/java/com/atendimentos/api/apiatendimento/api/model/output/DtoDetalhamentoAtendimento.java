package com.atendimentos.api.apiatendimento.api.model.output;

import java.time.OffsetDateTime;

import com.atendimentos.api.apiatendimento.domain.model.Atendimento;
import com.atendimentos.api.apiatendimento.domain.model.Cliente;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Servico;
import com.atendimentos.api.apiatendimento.domain.model.StatusAtendimento;


public record DtoDetalhamentoAtendimento(
    Long id,
    Fornecedor fornecedor,
    Cliente cliente,
    Servico servico,
    OffsetDateTime dataInicio,
    OffsetDateTime dataFim,
    StatusAtendimento statusAtendimento
) {
    public DtoDetalhamentoAtendimento(Atendimento atendimento){
        this(atendimento.getId(), atendimento.getFornecedor(), atendimento.getCliente(), atendimento.getServico(), 
        atendimento.getDataInicio(), atendimento.getDataFim(), atendimento.getStatusAtendimento());
    }
}
