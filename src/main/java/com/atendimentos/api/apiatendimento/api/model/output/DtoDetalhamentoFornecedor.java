package com.atendimentos.api.apiatendimento.api.model.output;

import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.TipoFornecedor;


public record DtoDetalhamentoFornecedor(Long id, String nomeFornecedor, TipoFornecedor tipoFornecedor, String cpf, String cnpj, String telefone, String email) {
    public DtoDetalhamentoFornecedor(Fornecedor fornecedor){
        this(fornecedor.getId(), fornecedor.getNomeFornecedor(), fornecedor.getTipoFornecedor(), 
        fornecedor.getCpf(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getEmail());
    }
}
