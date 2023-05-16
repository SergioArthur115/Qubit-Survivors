create schema qubits_Survivors;
use qubits_Survivors;

create table jogadores(
	id_jogador INT PRIMARY KEY AUTO_INCREMENT,
    nome_jogador varchar(45) NOT null
);

create table partidas(
	id_partida INT PRIMARY KEY AUTO_INCREMENT,
    nome_mapa varchar(45) NOT null,
    data_partida date not null,
    pontuacao int not null,
    duracao datetime not null,
    id_jogador int,
    FOREIGN KEY (id_jogador) REFERENCES jogadores(id_jogador)
);

create table itens(
	id_item INT PRIMARY KEY AUTO_INCREMENT,
    nome_item varchar(45) NOT null,
    descricao_item varchar(45) not null,
    tipo_item varchar(45) not null,
    raridade_item varchar(45) not null,
    carac_item varchar(45) not null
);

create table jogadores_itens(
	id_jogador int,
	id_item int,
	PRIMARY KEY (id_jogador, id_item),
    FOREIGN KEY (id_jogador) REFERENCES jogadores(id_jogador),
    FOREIGN KEY (id_item) REFERENCES itens(id_item)
);