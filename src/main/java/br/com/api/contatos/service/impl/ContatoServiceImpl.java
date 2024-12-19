package br.com.api.contatos.service.impl;

import br.com.api.contatos.dto.ContatoBuscarDTO;
import br.com.api.contatos.dto.ContatoSalvarAlterarDTO;
import br.com.api.contatos.error.exception.ObjectNotFoundException;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.model.Contato;
import br.com.api.contatos.repository.ContatoRepository;
import br.com.api.contatos.service.CodigoPostalService;
import br.com.api.contatos.service.ContatoService;
import br.com.api.contatos.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ContatoServiceImpl implements ContatoService {

    public static final String MENSAGEM_ID_NAO_ENCONTRADO = "Contato de id %s não foi encontrado.";
    public static final String BUSCANDO_UM_CONTATO_PELO_ID = "Buscando um contato pelo id {}";
    public static final String SALVANDO_UM_CONTATO = "Salvando um contato";
    public static final String ALTERANDO_UM_CONTATO = "Alterando um contato";
    public static final String EXCLUINDO_UM_CONTATO = "Excluindo um contato";

    private final ContatoRepository contatoRepository;
    private final CodigoPostalService codigoPostalService;

    @Override
    public Page<ContatoBuscarDTO> buscarTodosOsContatosPorNome(String nome, Pageable pageable) {
        Page<Contato> contatos = nome == null || nome.isEmpty()
                ? contatoRepository.findAll(pageable)
                : contatoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        return contatos.map(ContatoBuscarDTO::toDTO);
    }

    @Override
    public Contato buscarPorId(Long id) {
        log.info(BUSCANDO_UM_CONTATO_PELO_ID, id);
        return contatoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format(MENSAGEM_ID_NAO_ENCONTRADO, id)));
    }

    @Override
    public Contato salvar(ContatoSalvarAlterarDTO contatoSalvarDTO) {
        log.info(SALVANDO_UM_CONTATO);
        final Contato contato = new Contato();
        return salvar(contatoSalvarDTO, contato);
    }


    @Override
    public Contato alterar(Long id, ContatoSalvarAlterarDTO contatoAlterarDTO) {
        log.info(ALTERANDO_UM_CONTATO);
        final Contato contato = buscarPorId(id);
        return salvar(contatoAlterarDTO, contato);
    }

    @Override
    public void deletar(Long id) {
        log.info(EXCLUINDO_UM_CONTATO);
        buscarPorId(id);
        contatoRepository.deleteById(id);
    }

    private Contato salvar(ContatoSalvarAlterarDTO contatoSalvarDTO, Contato contato) {

        final String cep = StringUtil.removerCaracteresEspeciais(contatoSalvarDTO.cep());

        contato.setNome(contatoSalvarDTO.nome());
        contato.setTelefone(contatoSalvarDTO.telefone());
        contato.setEmail(contatoSalvarDTO.email());
        contato.setNumero(contatoSalvarDTO.numero());

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(cep);
        contato.setCodigoPostal(codigoPostal);

        return contatoRepository.save(contato);
    }

}
