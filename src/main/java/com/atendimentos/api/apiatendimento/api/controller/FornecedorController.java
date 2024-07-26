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

import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarFornecedor;
import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarFornecedor;
import com.atendimentos.api.apiatendimento.api.model.output.DtoDetalhamentoFornecedor;
import com.atendimentos.api.apiatendimento.api.model.output.DtoListaFornecedores;
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
    @Transactional
    public ResponseEntity<DtoDetalhamentoFornecedor> cadastrar(@Valid @RequestBody DtoCadastrarFornecedor dados, UriComponentsBuilder uriBuilder){
        Fornecedor fornecedor = new Fornecedor(dados);
        fornecedorService.salvar(fornecedor);
        var uri = uriBuilder.path("/fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoFornecedor(fornecedor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDetalhamentoFornecedor> listarFornecedor(@PathVariable Long id){
        Fornecedor fornecedor = fornecedorService.listarFornecedor(id);
        return ResponseEntity.ok().body(new DtoDetalhamentoFornecedor(fornecedor));
    }

    @GetMapping
    public ResponseEntity<Page<DtoListaFornecedores>> listarFornecedores(@PageableDefault(size = 10, sort = {"nomeFornecedor"}) Pageable paginacao){
        var page = fornecedorService.listarFornecedores(paginacao).map(DtoListaFornecedores::new);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> inativarFornecedor(@PathVariable Long id){
        Fornecedor fornecedor = fornecedorService.listarFornecedor(id);
        fornecedorService.inativarFornecedor(fornecedor.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoDetalhamentoFornecedor> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid DtoAtualizarFornecedor dados){
        Fornecedor fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, dados);
        return ResponseEntity.ok().body(new DtoDetalhamentoFornecedor(fornecedorAtualizado));
    }
}
