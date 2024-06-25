package com.atendimentos.api.apiatendimento.domain.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.atendimentos.api.apiatendimento.domain.exception.EntidadeNaoEncontradaException;
import com.atendimentos.api.apiatendimento.domain.exception.NegocioException;
import com.atendimentos.api.apiatendimento.domain.model.Cliente;
import com.atendimentos.api.apiatendimento.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    

    @Transactional
    public Cliente salvar(Cliente novoCliente){
        Cliente cliente = new Cliente();
        if(cliente.clienteMaiorDeIdade(novoCliente.getDataNascimento())){
            throw new NegocioException("Cliente precisa ser maior de 18 anos");
        }
        
        novoCliente.setAtivo(true);
        return clienteRepository.save(novoCliente);
    }

    public Page<Cliente> listarClientes(Pageable paginacao){
        return clienteRepository.findAll(paginacao);
    }

    public Optional<Cliente> listarCliente(Long id){
        return clienteRepository.findById(id);
    }

    public void inativarCliente(Long id){
        Cliente cliente = this.buscar(id);
    
        cliente.setAtivo(false); // Supondo que o Cliente tem um campo `ativo` do tipo boolean
        clienteRepository.save(cliente);

    }

    public Cliente buscar(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado com id: " + id));
    }

}
