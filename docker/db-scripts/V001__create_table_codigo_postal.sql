CREATE TABLE codigo_postal
(
    cep           VARCHAR(20)  NOT NULL PRIMARY KEY,
    logradouro    VARCHAR(100) NOT NULL,
    bairro        VARCHAR(30)  NOT NULL,
    cidade        VARCHAR(30)  NOT NULL,
    estado        VARCHAR(30)  NOT NULL,
    json          JSONB        NOT NULL,
    data_consulta TIMESTAMP    NOT NULL
);