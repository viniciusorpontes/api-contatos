package br.com.api.contatos.service;

import br.com.api.contatos.dto.ContatoDTO;
import br.com.api.contatos.model.Contato;
import org.springframework.stereotype.Service;

public interface ContatoService {

    Contato buscarPorId(Long id);

    Contato salvar(ContatoDTO contatoDTO);

}
