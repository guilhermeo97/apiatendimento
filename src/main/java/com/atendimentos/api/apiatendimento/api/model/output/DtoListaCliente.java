package com.atendimentos.api.apiatendimento.api.model.output;

import java.time.LocalDate;

import com.atendimentos.api.apiatendimento.domain.model.Cliente;

public record DtoListaCliente(Long id, String nomeCliente, LocalDate dataNascimento, String email, String telefone) {
    
    public DtoListaCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNomeCliente(), cliente.getDataNascimento(), cliente.getEmail(), cliente.getTelefone());
    }
}
