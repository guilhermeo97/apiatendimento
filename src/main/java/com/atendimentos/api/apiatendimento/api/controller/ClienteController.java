package com.atendimentos.api.apiatendimento.api.controller;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.atendimentos.api.apiatendimento.api.model.input.DtoAtualizarCliente;
import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastroCliente;
import com.atendimentos.api.apiatendimento.api.model.output.DtoDetalhamentoCliente;
import com.atendimentos.api.apiatendimento.api.model.output.DtoListaCliente;
import com.atendimentos.api.apiatendimento.domain.model.Cliente;
import com.atendimentos.api.apiatendimento.domain.service.ClienteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    
    private final ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoDetalhamentoCliente> salvar(@Valid @RequestBody DtoCadastroCliente novoCliente, UriComponentsBuilder uriBuilder){
       Cliente cliente = new Cliente(novoCliente);
       clienteService.salvar(cliente);
       var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
       return ResponseEntity.created(uri).body(new DtoDetalhamentoCliente(cliente));
    }


    @GetMapping
    public  ResponseEntity<Page<DtoListaCliente>> listarClientes(@PageableDefault(size = 10, sort = {"nomeCliente"}) Pageable paginacao){
        var page = clienteService.listarClientes(paginacao).map(DtoListaCliente::new);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDetalhamentoCliente> listarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.listarCliente(id);
        return ResponseEntity.ok().body(new DtoDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.listarCliente(id);
        cliente.excluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoDetalhamentoCliente> atualizar(@PathVariable Long id, @RequestBody @Valid DtoAtualizarCliente dados) {
        Cliente cliente = clienteService.listarCliente(id);
        cliente.atualizarInformacoes(dados);
        clienteService.salvar(cliente);
        return ResponseEntity.ok(new DtoDetalhamentoCliente(cliente));
    }

}
