CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users.user(
	id bigserial PRIMARY KEY,
	nome varchar(100) NOT NULL,
	cpf varchar(100) NOT NULL,
	endereco varchar(100) NOT NULL, 
	email varchar(100) NOT NULL, 
	telefone varchar(100) NOT NULL, 
	data_cadastro timestamp NOT NULL 
);