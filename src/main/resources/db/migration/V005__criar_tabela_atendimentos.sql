create table atendimentos.atendimento (
	id bigint not null auto_increment,
	id_cliente bigint not null,
    id_servico bigint not null,
    id_fornecedor bigint not null,
    data_inicio timestamp not null,
    data_fim timestamp not null,
    status_atendimento ENUM('AGENDADO', 'CANCELADO', 'CONCLUÍDO') not null default 'AGENDADO',
    
    
    primary key(id),
    foreign key(id_fornecedor) references fornecedor(id),
    foreign key(id_cliente) references cliente(id),
    foreign key(id_servico) references servico(id)
)