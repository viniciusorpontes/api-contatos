package br.com.api.contatos.controller;

import br.com.api.contatos.dto.ContatoBuscarDTO;
import br.com.api.contatos.dto.ContatoSalvarAlterarDTO;
import br.com.api.contatos.model.Contato;
import br.com.api.contatos.service.ContatoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contatos")
@AllArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ContatoBuscarDTO> buscarContatosPorNome(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return contatoService.buscarTodosOsContatosPorNome(nome, pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContatoBuscarDTO> buscarPorId(@PathVariable Long id) {
        final Contato contato = contatoService.buscarPorId(id);
        return ResponseEntity.ok().body(ContatoBuscarDTO.toDTO(contato));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContatoBuscarDTO> salvar(@RequestBody @Valid ContatoSalvarAlterarDTO constaSalvarDTO) {
        final Contato contato = contatoService.salvar(constaSalvarDTO);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).body(ContatoBuscarDTO.toDTO(contato));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContatoBuscarDTO> alterar(@PathVariable Long id, @RequestBody @Valid ContatoSalvarAlterarDTO contatoAlterarDTO) {
        final Contato contato = this.contatoService.alterar(id, contatoAlterarDTO);
        return ResponseEntity.ok().body(ContatoBuscarDTO.toDTO(contato));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
