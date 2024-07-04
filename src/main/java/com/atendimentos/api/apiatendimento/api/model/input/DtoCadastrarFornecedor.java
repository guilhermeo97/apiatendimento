package com.atendimentos.api.apiatendimento.api.model.input;

import com.atendimentos.api.apiatendimento.domain.model.TipoFornecedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCadastrarFornecedor(
    @NotBlank(message = "Campo obrigatório.")
    String nomeFornecedor,

    @NotNull(message = "Campo obrigatório.")
    TipoFornecedor tipoFornecedor,
    
    String cpf,
    String cnpj,

    @NotBlank
    String telefone,

    @NotBlank
    @Email
    String email
) {
    
}
