create table atendimentos.fornecedor (
	id bigint not null auto_increment,
	nome_fornecedor varchar(255) not null,
    tipo_fornecedor text not null,
    cpf varchar(14), 
    cnpj varchar(14),
    telefone varchar(20) not null,
    email varchar(255) not null unique,
    ativo tinyint not null default 1,
    primary key(id)
)