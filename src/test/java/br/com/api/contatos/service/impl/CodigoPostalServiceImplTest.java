package br.com.api.contatos.service.impl;

import br.com.api.contatos.client.CepClient;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.repository.CodigoPostalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CodigoPostalServiceImplTest {

    @Mock
    private CodigoPostalRepository codigoPostalRepository;

    @Mock
    private CepClient cepClient;

    @InjectMocks
    private CodigoPostalServiceImpl codigoPostalService;

    @Test
    void buscarCepSalvoComSucesso() {

        final String cep = "18682841";

        final CodigoPostal codigoPostalMock = new CodigoPostal();
        codigoPostalMock.setCep(cep);
        codigoPostalMock.setJson("{\"cep\":\"18682-841\",\"logradouro\":\"Rua José Príncipe Penhafiel\",\"bairro\":\"Jardim Príncipe\",\"localidade\":\"Lençóis Paulista\",\"estado\":\"São Paulo\"}");
        codigoPostalMock.setDataConsulta(LocalDateTime.now());

        Mockito.when(codigoPostalRepository.findById(cep)).thenReturn(Optional.of(codigoPostalMock));

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(cep);

        Assertions.assertThat(codigoPostal).isNotNull().isEqualTo(codigoPostalMock);
    }

}