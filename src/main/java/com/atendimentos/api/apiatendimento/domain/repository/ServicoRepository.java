package com.atendimentos.api.apiatendimento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atendimentos.api.apiatendimento.domain.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
