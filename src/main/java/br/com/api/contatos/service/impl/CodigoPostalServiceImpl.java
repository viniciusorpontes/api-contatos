package br.com.api.contatos.service.impl;

import br.com.api.contatos.client.CepClient;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.repository.CodigoPostalRepository;
import br.com.api.contatos.service.CodigoPostalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CodigoPostalServiceImpl implements CodigoPostalService {

    private final CodigoPostalRepository codigoPostalRepository;
    private final CepClient cepClient;

    @Override
    public CodigoPostal buscarCep(String cep) {
        return codigoPostalRepository.findById(cep).orElse(buscarCepWebService(cep));
    }

    private CodigoPostal buscarCepWebService(String cep) {
        final String response = cepClient.buscarCep(cep);
        final CodigoPostal codigoPostal = new CodigoPostal(response);
        return codigoPostalRepository.save(codigoPostal);
    }

}
