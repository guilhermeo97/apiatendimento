create table servico (
	id bigint not null auto_increment,
	nome_servico varchar(255) not null,
    id_fornecedor bigint not null,
    custo_servico decimal(10, 2) not null,
    ativo tinyint not null default 1,
    
    primary key(id),
    foreign key(id_fornecedor) references fornecedor(id)
)