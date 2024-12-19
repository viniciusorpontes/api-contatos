CREATE TABLE codigo_postal (
    cep           VARCHAR(20)  NOT NULL PRIMARY KEY,
    json          VARCHAR(255) NOT NULL,
    data_consulta TIMESTAMP    NOT NULL
);