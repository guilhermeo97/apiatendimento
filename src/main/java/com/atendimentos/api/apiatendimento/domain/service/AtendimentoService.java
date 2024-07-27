package com.atendimentos.api.apiatendimento.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atendimentos.api.apiatendimento.domain.model.Atendimento;
import com.atendimentos.api.apiatendimento.domain.model.Cliente;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Servico;
import com.atendimentos.api.apiatendimento.domain.model.StatusAtendimento;
import com.atendimentos.api.apiatendimento.domain.repository.AtendimentoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AtendimentoService {
    
    private final AtendimentoRepository atendimentoRepository;
    private final FornecedorService fornecedorService;
    private final ClienteService clienteService;
    private final ServicoService servicoService;


    @Transactional
    public Atendimento salvar(Atendimento atendimento){
        Fornecedor fornecedor = fornecedorService.listarFornecedor(atendimento.getFornecedor().getId());
        Cliente cliente = clienteService.listarCliente(atendimento.getCliente().getId());
        Servico servico = servicoService.listarServico(atendimento.getServico().getId());
        atendimento.setFornecedor(fornecedor);
        atendimento.setCliente(cliente);
        atendimento.setServico(servico);
        atendimento.setStatusAtendimento(StatusAtendimento.AGENDADO);
        return atendimentoRepository.save(atendimento);
    }

}
