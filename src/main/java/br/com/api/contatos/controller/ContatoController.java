package br.com.api.contatos.controller;

import br.com.api.contatos.dto.ContatoDTO;
import br.com.api.contatos.model.Contato;
import br.com.api.contatos.service.ContatoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contatos")
@AllArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(contatoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Contato> salvar(@RequestBody ContatoDTO contatoDTO) {
        final Contato contato = contatoService.salvar(contatoDTO);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).body(contato);
    }

}
