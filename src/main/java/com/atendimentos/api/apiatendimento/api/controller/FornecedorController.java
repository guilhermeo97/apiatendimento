package com.atendimentos.api.apiatendimento.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarFornecedor;
import com.atendimentos.api.apiatendimento.api.model.output.DtoDetalhamentoFornecedor;
import com.atendimentos.api.apiatendimento.domain.model.Fornecedor;
import com.atendimentos.api.apiatendimento.domain.service.FornecedorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/fornecedores")
@AllArgsConstructor
public class FornecedorController {
    
    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<DtoDetalhamentoFornecedor> cadastrar(@Valid @RequestBody DtoCadastrarFornecedor dados, UriComponentsBuilder uriBuilder){
        Fornecedor fornecedor = new Fornecedor(dados);
        fornecedorService.salvar(fornecedor);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(fornecedor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoFornecedor(fornecedor));
    }
}
