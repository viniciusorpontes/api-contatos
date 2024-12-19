package br.com.api.contatos.service.impl;

import br.com.api.contatos.dto.ContatoSalvarAlterarDTO;
import br.com.api.contatos.error.exception.ObjectNotFoundException;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.model.Contato;
import br.com.api.contatos.repository.ContatoRepository;
import br.com.api.contatos.service.CodigoPostalService;
import br.com.api.contatos.service.ContatoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ContatoServiceImpl implements ContatoService {

    public static final String MENSAGEM_ID_NAO_ENCONTRADO = "Contato de id %s não foi encontrado.";
    public static final String BUSCANDO_UM_CONTATO_PELO_ID = "Buscando um contato pelo id {}";

    private final ContatoRepository contatoRepository;
    private final CodigoPostalService codigoPostalService;

    @Override
    public Contato buscarPorId(Long id) {
        log.info(BUSCANDO_UM_CONTATO_PELO_ID, id);
        return contatoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format(MENSAGEM_ID_NAO_ENCONTRADO, id)));
    }

    @Override
    public Contato salvar(ContatoSalvarAlterarDTO contatoSalvarDTO) {

        final Contato contato = new Contato();
        contato.setNome(contatoSalvarDTO.nome());
        contato.setTelefone(contatoSalvarDTO.telefone());
        contato.setEmail(contatoSalvarDTO.email());
        contato.setNumero(contatoSalvarDTO.numero());

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(contatoSalvarDTO.cep());
        contato.setCodigoPostal(codigoPostal);

        return contatoRepository.save(contato);
    }

    @Override
    public Contato alterar(Long id, ContatoSalvarAlterarDTO contatoAlterarDTO) {
        final Contato contato = buscarPorId(id);

        contato.setNome(contatoAlterarDTO.nome());
        contato.setTelefone(contatoAlterarDTO.telefone());
        contato.setEmail(contatoAlterarDTO.email());
        contato.setNumero(contatoAlterarDTO.numero());

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(contatoAlterarDTO.cep());
        contato.setCodigoPostal(codigoPostal);

        return contatoRepository.save(contato);
    }

}
