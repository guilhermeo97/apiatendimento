package com.atendimentos.api.apiatendimento.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarAtendimento;
import com.atendimentos.api.apiatendimento.api.model.output.DtoDetalhamentoAtendimento;
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

}
