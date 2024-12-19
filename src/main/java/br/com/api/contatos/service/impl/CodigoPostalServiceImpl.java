package br.com.api.contatos.service.impl;

import br.com.api.contatos.client.CepClient;
import br.com.api.contatos.error.exception.GenericException;
import br.com.api.contatos.error.exception.ObjectNotFoundException;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.repository.CodigoPostalRepository;
import br.com.api.contatos.service.CodigoPostalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@AllArgsConstructor
@Service
public class CodigoPostalServiceImpl implements CodigoPostalService {

    public static final String ERRO_AO_BUSCAR_CEP_STACK_TRACE = "Erro ao buscar CEP: {} - Stack Trace: {}";
    public static final String ERRO_AO_BUSCAR_O_CEP = "Erro ao buscar o CEP";
    public static final String CEP_NAO_ENCONTRADO = "CEP não encontrado";
    public static final String BUSCANDO_CEP = "Buscando CEP: {}";

    private final CodigoPostalRepository codigoPostalRepository;
    private final CepClient cepClient;

    @Override
    public CodigoPostal buscarCep(String cep) {
        log.info(BUSCANDO_CEP, cep);
        return codigoPostalRepository.findById(cep).orElse(buscarCepWebService(cep));
    }

    private CodigoPostal buscarCepWebService(String cep) {
        String response;
        try {
            response = cepClient.buscarCep(cep);
        } catch (WebClientResponseException.NotFound ex) {
            throw new ObjectNotFoundException(CEP_NAO_ENCONTRADO);
        } catch (Exception ex) {
            log.error(ERRO_AO_BUSCAR_CEP_STACK_TRACE, ex.getMessage(), ex.getStackTrace());
            throw new GenericException(ERRO_AO_BUSCAR_O_CEP);
        }
        return salvar(cep, response);
    }

    private CodigoPostal salvar(String cep, String response) {
        final CodigoPostal codigoPostal = new CodigoPostal(cep, response);
        return codigoPostalRepository.save(codigoPostal);
    }

}
