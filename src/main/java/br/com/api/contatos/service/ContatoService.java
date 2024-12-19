package br.com.api.contatos.service;

import br.com.api.contatos.dto.ContatoBuscarDTO;
import br.com.api.contatos.dto.ContatoSalvarAlterarDTO;
import br.com.api.contatos.model.Contato;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoService {

    Page<ContatoBuscarDTO> buscarTodosOsContatosPorNome(String nome, Pageable pageable);

    Contato buscarPorId(Long id);

    Contato salvar(ContatoSalvarAlterarDTO contatoSalvarAlterarDTO);

    Contato alterar(Long id, @Valid ContatoSalvarAlterarDTO contatoAlterarDTO);

    void deletar(Long id);

}
