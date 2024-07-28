package com.atendimentos.api.apiatendimento.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarAtendimento;
import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarAtendimento;
import com.atendimentos.api.apiatendimento.api.model.output.DtoDetalhamentoAtendimento;
import com.atendimentos.api.apiatendimento.api.model.output.DtoListaAtendimento;
import com.atendimentos.api.apiatendimento.domain.model.Atendimento;
import com.atendimentos.api.apiatendimento.domain.service.AtendimentoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/atendimentos")
@AllArgsConstructor
public class AtendimentoController {
    
    private final AtendimentoService atendimentoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoDetalhamentoAtendimento> cadastrar(@RequestBody @Valid DtoCadastrarAtendimento dados, UriComponentsBuilder uriBuilder){
        Atendimento atendimento = new Atendimento(dados);
        atendimentoService.salvar(atendimento);
        var uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(atendimento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoAtendimento(atendimento));
    }

    @GetMapping
    public ResponseEntity<Page<DtoListaAtendimento>> listarAtendimentos(@PageableDefault(size = 10, sort = {"nomeCliente"}) Pageable paginacao){
        var page = atendimentoService.listarAtendimentos(paginacao).map(DtoListaAtendimento::new);
        return ResponseEntity.ok().body(page); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDetalhamentoAtendimento> listarAtendimento(@PathVariable Long id){
        Atendimento atendimento = atendimentoService.listarAtendimento(id);
        return ResponseEntity.ok().body(new DtoDetalhamentoAtendimento(atendimento));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> cancelarAtendimento(@PathVariable Long id){
        Atendimento atendimento = atendimentoService.listarAtendimento(id);
        atendimentoService.cancelarAtendimento(atendimento.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoDetalhamentoAtendimento> atualizarAtendimento(@PathVariable Long id, @RequestBody DtoAtualizarAtendimento dados){
        Atendimento atendimento = atendimentoService.atualizarInformacoes(id, dados);
        return ResponseEntity.ok().body(new DtoDetalhamentoAtendimento(atendimento));
        
    }
}
