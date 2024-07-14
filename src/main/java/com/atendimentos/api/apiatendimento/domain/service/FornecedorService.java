package com.atendimentos.api.apiatendimento.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarFornecedor;
import com.atendimentos.api.apiatendimento.domain.exception.EntidadeNaoEncontradaException;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.repository.FornecedorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FornecedorService {
    
    private final FornecedorRepository fornecedorRepository;

    @Transactional
    public Fornecedor salvar(Fornecedor fornecedor){
        fornecedor.setAtivo(true);
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public Fornecedor atualizarFornecedor(Long id, DtoAtualizarFornecedor fornecedorAtualizado){
        Fornecedor fornecedor = this.listarFornecedor(id);
        //fornecedor.atualizarInformacoes(fornecedorAtualizado);
        if(fornecedorAtualizado.nomeFornecedor() != null){
            fornecedor.setNomeFornecedor(fornecedorAtualizado.nomeFornecedor());
        }
        if(fornecedorAtualizado.telefone() != null){
            fornecedor.setTelefone(fornecedorAtualizado.telefone());
        }   
        if(fornecedorAtualizado.cpf() != null){
            fornecedor.setCpf(fornecedorAtualizado.cpf());
        }

        if(fornecedorAtualizado.cnpj() != null){
            fornecedor.setCnpj(fornecedorAtualizado.cnpj());
        }
        if(fornecedorAtualizado.email() != null){
            fornecedor.setEmail(fornecedorAtualizado.email());
        }

        if(fornecedorAtualizado.tipoFornecedor() != null){
            fornecedor.setTipoFornecedor(fornecedorAtualizado.tipoFornecedor());
        }
        return fornecedorRepository.save(fornecedor);
    }

    public Page<Fornecedor> listarFornecedores(Pageable paginacao){
        return fornecedorRepository.findAll(paginacao);
    }
    
    public Fornecedor listarFornecedor(Long id){
        return fornecedorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado com id: " + id));
    }

    @Transactional
    public void inativarFornecedor(Long id){
        //busca o fornecedor e cria uma instância se for encontrado
        Fornecedor fornecedor = this.listarFornecedor(id);
        //muda o status do fornecedor para inativo
        fornecedor.excluir();
        //salva no db
        fornecedorRepository.save(fornecedor);

    }

    



}
