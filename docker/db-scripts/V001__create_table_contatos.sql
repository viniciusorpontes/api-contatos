CREATE TABLE contatos
(
    id       SERIAL       NOT NULL PRIMARY KEY,
    nome     VARCHAR(50)  NOT NULL,
    telefone VARCHAR(20)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    cep      VARCHAR(20)  NOT NULL,
    FOREIGN KEY (cep) REFERENCES codigo_postal(cep)
);