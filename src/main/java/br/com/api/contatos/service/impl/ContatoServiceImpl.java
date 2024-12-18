package br.com.api.contatos.service.impl;

import br.com.api.contatos.dto.ContatoDTO;
import br.com.api.contatos.exception.ObjectNotFoundException;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.model.Contato;
import br.com.api.contatos.repository.ContatoRepository;
import br.com.api.contatos.service.CodigoPostalService;
import br.com.api.contatos.service.ContatoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContatoServiceImpl implements ContatoService {

    public static final String MENSAGEM_ID_NAO_ENCONTRADO = "Contato de id %s não foi encontrado.";

    private final ContatoRepository contatoRepository;
    private final CodigoPostalService codigoPostalService;

    @Override
    public Contato buscarPorId(Long id) {
        return contatoRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(String.format(MENSAGEM_ID_NAO_ENCONTRADO, id)));
    }

    @Override
    public Contato salvar(ContatoDTO contatoDTO) {

        final Contato contato = new Contato();
        contato.setNome(contatoDTO.nome());
        contato.setTelefone(contatoDTO.telefone());
        contato.setEmail(contatoDTO.email());

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(contatoDTO.cep());
        contato.setCodigoPostal(codigoPostal);

        return contatoRepository.save(contato);
    }

}
