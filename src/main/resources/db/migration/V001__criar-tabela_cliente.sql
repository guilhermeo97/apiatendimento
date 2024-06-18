create table cliente(
	id bigint not null auto_increment,
    nome_cliente varchar(100) not null,
    data_nascimento date,
    ativo boolean default TRUE,
    email varchar(100) not null unique,
    telefone varchar(15) not null,
    
    primary key(id)
)