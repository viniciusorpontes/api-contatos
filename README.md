
# API de Gestão de Contatos

Esta é uma API REST desenvolvida para gerenciar contatos. Ela permite criar, listar, atualizar e excluir contatos, 
oferecendo funcionalidades como busca filtrada e validações de dados.

## Índice
- [Tecnologias](#tecnologias)
- [Requisitos](#requisitos)
- [Execução](#execução)
- [Endpoints](#endpoints)

---

## Tecnologias
- **Java**: Linguagem principal para desenvolvimento
- **Spring Boot**: Framework para criação da API
- **PostgreSQL**: Banco de dados relacional
- **Wiremock**: Mock da API de CEP
- **Docker**: Containerização do ambiente

---

## Requisitos
- Java 17
- Maven
- Docker

---

## Execução

1. Execute o docker compose que está na pasta "docker":
   ```bash
   docker compose up -d 
   ```

2. Inicie a aplicação pela IDE ou executando o comando a seguir para compilar a aplicação:
   ```bash
   mvn clean package
   ```

3. (Caso tenha compilado a aplicação manualmente) rode esse comando para iniciar a aplicação:
   ```bash
   cd target
   ```
   ```bash
   java -jar .\api-contatos-0.0.1-SNAPSHOT.jar
   ```

---

## Endpoints
### **Os endpoints estão disponíveis no arquivo "teste-api.http"**
