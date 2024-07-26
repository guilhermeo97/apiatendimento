package com.atendimentos.api.apiatendimento.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarServico;
import com.atendimentos.api.apiatendimento.domain.exception.EntidadeNaoEncontradaException;
import com.atendimentos.api.apiatendimento.domain.model.Servico;
import com.atendimentos.api.apiatendimento.domain.repository.ServicoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicoService {
    
    private final ServicoRepository servicoRepository;

    @Transactional
    public Servico cadastrar(Servico servico){
        servico.setAtivo(true);
        return servicoRepository.save(servico);
    }

    public Page<Servico> listarServicos(Pageable paginacao){
        return servicoRepository.findAll(paginacao);
    }

    public Servico listarServico(Long id){
        return servicoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado para esse Fornecedor."));
    }

    @Transactional
    public Servico atualizarServico(Long id, DtoAtualizarServico servicoAtualizado){
        Servico servico = this.listarServico(id);
        if(servicoAtualizado.nomeServico() != null){
            servico.setNomeServico(servicoAtualizado.nomeServico());
        }

        if(servicoAtualizado.fornecedor() != null){
            servico.setFornecedor(servicoAtualizado.fornecedor());
        }

        if(servicoAtualizado.custoServico() != null){
            servico.setCustoServico(servicoAtualizado.custoServico());
        }

        return servicoRepository.save(servico);

    }

    public void inativarServico(Long id){
        Servico servico = this.listarServico(id);
        servico.inativar();
        servicoRepository.save(servico);
    }

}
