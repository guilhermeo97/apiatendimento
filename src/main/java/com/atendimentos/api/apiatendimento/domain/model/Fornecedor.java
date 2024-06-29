package com.atendimentos.api.apiatendimento.domain.model;

import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarFornecedor;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@EqualsAndHashCode(of = "id")
public class Fornecedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String nomeFornecedor;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private TipoFornecedor tipoFornecedor;
    private String cpf;
    private String cnpj;

    @NotBlank
    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private boolean ativo;

    public Fornecedor(DtoCadastrarFornecedor dados){
        this.nomeFornecedor = dados.nomeFornecedor();
        if(ehFornecedorPessoaFisica(dados)){
            this.cpf = dados.cpf();
        }
        this.cnpj = dados.cnpj();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.ativo = true;
    }

    public boolean ehFornecedorPessoaFisica(DtoCadastrarFornecedor dados){
        return dados.tipoFornecedor().equals(TipoFornecedor.FÍSICA);
    }
}
