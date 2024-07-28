package com.atendimentos.api.apiatendimento.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarAtendimento;
import com.atendimentos.api.apiatendimento.domain.exception.EntidadeNaoEncontradaException;
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
        if(!fornecedor.getId().equals(servico.getFornecedor().getId())){
            throw new EntidadeNaoEncontradaException("Este serviço não pertence a esse fornecedor.");
        }
        atendimento.setFornecedor(fornecedor);
        atendimento.setCliente(cliente);
        atendimento.setServico(servico);
        atendimento.setStatusAtendimento(StatusAtendimento.AGENDADO);
        return atendimentoRepository.save(atendimento);
    }

    @Transactional
    public Atendimento atualizarInformacoes(Long id, DtoAtualizarAtendimento dados){
        Atendimento atendimento = this.listarAtendimento(id);
        if(dados.cliente() != null){
            atendimento.setCliente(dados.cliente());
        }

        if(dados.fornecedor() != null){
            atendimento.setFornecedor(dados.fornecedor());
        }

        if(dados.servico() != null){
            atendimento.setServico(dados.servico());
        }

        if(dados.servico() != null){
            atendimento.setServico(dados.servico());
        }

        if(dados.dataInicio() != null){
            atendimento.setDataInicio(dados.dataInicio());
        }

        if(dados.dataFim() != null){
            atendimento.setDataFim(dados.dataFim());
        }

        if(dados.statusAtendimento() != null){
            atendimento.setStatusAtendimento(dados.statusAtendimento());
        }

        return atendimentoRepository.save(atendimento);

    }

    @Transactional
    public void cancelarAtendimento(Long id){
        Atendimento atendimento = this.listarAtendimento(id);
        atendimento.cancelarAtendimento();
    }

    public Atendimento listarAtendimento(Long id){
        return atendimentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Atendimento não encontrado."));
    }

    public Page<Atendimento> listarAtendimentos(Pageable paginacao){
        return atendimentoRepository.findAll(paginacao);
    }

}
