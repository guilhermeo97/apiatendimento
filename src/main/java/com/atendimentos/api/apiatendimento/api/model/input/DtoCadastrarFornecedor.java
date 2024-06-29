package com.atendimentos.api.apiatendimento.api.model.input;

import com.atendimentos.api.apiatendimento.domain.model.TipoFornecedor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoCadastrarFornecedor(
    @NotBlank(message = "Campo obrigatório.")
    String nomeFornecedor,

    @NotBlank(message = "Campo obrigatório.")
    @Enumerated(value = EnumType.STRING)
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
