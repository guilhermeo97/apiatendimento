package com.atendimentos.api.apiatendimento.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarServico;
import com.atendimentos.api.apiatendimento.api.model.output.DtoDetalhamentoServico;
import com.atendimentos.api.apiatendimento.api.model.output.DtoListaServico;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Servico;
import com.atendimentos.api.apiatendimento.domain.service.FornecedorService;
import com.atendimentos.api.apiatendimento.domain.service.ServicoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/fornecedores/{id}/servicos")
@AllArgsConstructor
public class ServicoController {
    
    private final ServicoService servicoService;
    private final FornecedorService fornecedorService;

    @Transactional
    @PostMapping
    public ResponseEntity<DtoDetalhamentoServico> cadastrar(@PathVariable Long id ,@RequestBody @Valid DtoCadastrarServico servico, UriComponentsBuilder uriBuilder){
        Fornecedor fornecedor = fornecedorService.listarFornecedor(id);
        Servico novoServico = new Servico(servico);
        servicoService.cadastrar(novoServico);
        var uri = uriBuilder.path("/fornecedores/{id}/servicos/{id}").buildAndExpand(fornecedor.getId(), novoServico.getId()).toUri();
        //return ResponseEntity.created(uri).body(new DtoDetalhamentoServico(novoServico));
        return ResponseEntity.created(uri).body(new DtoDetalhamentoServico(novoServico));
    }

    @GetMapping
    public ResponseEntity<Page<DtoListaServico>> listarServicos(@PageableDefault(size = 10, sort = {"nomeServico"}) Pageable paginacao){
        var page = servicoService.listarServicos(paginacao).map(DtoListaServico::new);
        return ResponseEntity.ok().body(page);
    }

}
