package com.atendimentos.api.apiatendimento.domain.model;

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarFornecedor;
import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarFornecedor;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
    private String nomeFornecedor;

    @Enumerated(value = EnumType.STRING)
    private TipoFornecedor tipoFornecedor;
    
    private String cpf;
    private String cnpj;
    private String telefone;
    private String email;
    private boolean ativo;

    public Fornecedor(DtoCadastrarFornecedor dados){
        this.nomeFornecedor = dados.nomeFornecedor();
        if(ehFornecedorPessoaFisica(dados)){
            this.cpf = dados.cpf();
        }
        this.tipoFornecedor = dados.tipoFornecedor();
        this.cnpj = dados.cnpj();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.ativo = true;
    }

    public boolean ehFornecedorPessoaFisica(DtoCadastrarFornecedor dados){
        return dados.tipoFornecedor().equals(TipoFornecedor.FÍSICA);
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(Fornecedor dados) {
        if(dados.getNomeFornecedor() != null){
            this.setNomeFornecedor(dados.getNomeFornecedor());
        }
        if(dados.getTelefone() != null){
            this.setTelefone(dados.getTelefone());
        }   
        if(dados.getCpf() != null){
            this.setCpf(dados.getCpf());
        }

        if(dados.getCnpj() != null){
            this.setCnpj(dados.getCnpj());
        }
        if(dados.getEmail() != null){
            this.setEmail(dados.getEmail());
        }

        if(dados.getTipoFornecedor() != null){
            this.setTipoFornecedor(dados.getTipoFornecedor());
        }
    }
}

// String nomeFornecedor,


//     TipoFornecedor tipoFornecedor, ok
    
//     String cpf, ok
//     String cnpj, ok

//     String telefone, ok


//     String email ok