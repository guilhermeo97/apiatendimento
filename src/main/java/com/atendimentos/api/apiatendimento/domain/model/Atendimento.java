package com.atendimentos.api.apiatendimento.domain.model;

import java.time.OffsetDateTime;

import com.atendimentos.api.apiatendimento.api.model.input.DtoCadastrarAtendimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atendimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    private OffsetDateTime dataInicio;

    private OffsetDateTime dataFim;

    @Enumerated(value = EnumType.STRING)
    private StatusAtendimento statusAtendimento;

    public Atendimento(DtoCadastrarAtendimento dados){
        this.cliente = dados.cliente();
        this.fornecedor = dados.fornecedor();
        this.servico = dados.servico();
        this.dataInicio = dados.dataInicio();
        this.dataFim = dados.dataFim();
        this.statusAtendimento = StatusAtendimento.AGENDADO;
    }
}
