package com.atendimentos.api.apiatendimento.api.model.input;

import java.time.LocalDate;

public record DtoAtualizarCliente(

    String nomeCliente,
    LocalDate dataNascimento,
    String email,
    String telefone) {   
}
