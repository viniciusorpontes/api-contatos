package br.com.api.contatos.repository;

import br.com.api.contatos.model.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Page<Contato> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
