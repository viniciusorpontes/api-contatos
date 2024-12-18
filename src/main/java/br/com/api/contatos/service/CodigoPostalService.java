package br.com.api.contatos.service;

import br.com.api.contatos.model.CodigoPostal;
import org.springframework.stereotype.Service;

public interface CodigoPostalService {

    CodigoPostal buscarCep(String cep);

}
