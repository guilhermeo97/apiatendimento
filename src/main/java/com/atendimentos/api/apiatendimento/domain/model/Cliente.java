package com.atendimentos.api.apiatendimento.domain.model;


import java.time.LocalDate;

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarCliente;
import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastroCliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true )
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String nomeCliente;

    @NotNull
    private LocalDate dataNascimento;

    @JsonProperty(access = Access.READ_ONLY)
    private boolean ativo;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String telefone;

    public Cliente(DtoCadastroCliente dados) {
        this.ativo = true;
        this.nomeCliente = dados.nomeCliente();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataNascimento = dados.dataNascimento(); 
    }

    public boolean clienteMaiorDeIdade(LocalDate dataNascimento){
        if((LocalDate.now().getYear() - dataNascimento.getYear()) < 18){
            return true;
        }
        return false;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(@Valid DtoAtualizarCliente dados) {
        if(dados.nomeCliente() != null){
            this.nomeCliente = dados.nomeCliente();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }   
        if(dados.dataNascimento() != null){
            this.dataNascimento = dados.dataNascimento();
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
    }

}
