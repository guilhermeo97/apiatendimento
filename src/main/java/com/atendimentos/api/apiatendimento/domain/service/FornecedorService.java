package com.atendimentos.api.apiatendimento.domain.service;

import org.springframework.stereotype.Service;

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


}
