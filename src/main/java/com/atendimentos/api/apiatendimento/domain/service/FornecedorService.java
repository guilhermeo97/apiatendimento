package com.atendimentos.api.apiatendimento.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atendimentos.api.apiatendimento.domain.exception.EntidadeNaoEncontradaException;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.repository.FornecedorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FornecedorService {
    
    private final FornecedorRepository fornecedorRepository;

    public Fornecedor salvar(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public Page<Fornecedor> listarFornecedores(Pageable paginacao){
        return fornecedorRepository.findAll(paginacao);
    }
    
    public Fornecedor listarFornecedor(Long id){
        return fornecedorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado com id: " + id));
    }

    public void inativarCliente(Long id){
        //busca o fornecedor e cria uma instância se for encontrado
        Fornecedor fornecedor = this.listarFornecedor(id);
        //muda o status do fornecedor para inativo
        fornecedor.setAtivo(false);
        //salva no db
        fornecedorRepository.save(fornecedor);

    }

    



}
