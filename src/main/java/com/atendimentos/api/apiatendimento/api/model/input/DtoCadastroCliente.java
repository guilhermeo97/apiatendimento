package com.atendimentos.api.apiatendimento.api.model.input;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCadastroCliente(
    @NotBlank(message = "Nome é obrigatório")
    String nomeCliente,

    @NotNull
    LocalDate dataNascimento,

    @Email
    @NotBlank(message = "Email é obrigatório")
    String email,

    @NotBlank(message = "Telefone é obrigatório")
    String telefone) {
} 

    

