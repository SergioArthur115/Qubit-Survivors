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

INSERT INTO itens (nome_item,descricao_item,tipo_item,raridade_item,carac_item) VALUES
	('Espada de Qubits','Uma espada feita de qubits','Arma','Comum','Dano = 2 && velocidade = 1.0'),
	('Calculadora','Uma calculadora de qubits','Utilidade','Incomum','Dano += 1.5'),
	('Machado de Qubits','Um machado feito de qubits','Arma','Raro','Dano = 4 && frequencia = 0.5'),
	('Pistola de Qubits','Uma pistola feita de qubits','Arma','Épico','Dano = 10 && frequencia = 2.0'),
	('Botas de Qubit','Botas feitas de Qubits','Utilidade','Lendário','velocidade +=2');
    
    select * from itens;