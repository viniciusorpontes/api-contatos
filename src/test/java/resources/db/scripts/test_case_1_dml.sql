insert into codigo_postal (cep, json, data_consulta)
values ('12345-678', '{"cep":"18682-841","logradouro":"Rua José Príncipe Penhafiel","bairro":"Jardim Príncipe","localidade":"Lençóis Paulista","estado":"São Paulo"}', '2024-12-18 22:00:00');

insert into contatos (id, nome, telefone, email, cep, numero)
values (1,'João da Silva', '99999-9999', 'joao.silva@example.com', '12345-678', '123');

commit;