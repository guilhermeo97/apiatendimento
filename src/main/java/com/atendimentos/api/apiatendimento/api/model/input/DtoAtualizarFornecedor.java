package com.atendimentos.api.apiatendimento.api.model.input;

import com.atendimentos.api.apiatendimento.domain.model.TipoFornecedor;


public record DtoAtualizarFornecedor(
    String nomeFornecedor,
    TipoFornecedor tipoFornecedor,
    String cpf,
    String cnpj,
    String telefone,
    String email
) {

    
}
