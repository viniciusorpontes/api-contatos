package br.com.api.contatos.service;

import br.com.api.contatos.dto.ContatoSalvarAlterarDTO;
import br.com.api.contatos.model.Contato;
import jakarta.validation.Valid;

public interface ContatoService {

    Contato buscarPorId(Long id);

    Contato salvar(ContatoSalvarAlterarDTO contatoSalvarAlterarDTO);

    Contato alterar(Long id, @Valid ContatoSalvarAlterarDTO contatoAlterarDTO);
}
