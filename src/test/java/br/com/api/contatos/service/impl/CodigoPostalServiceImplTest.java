package br.com.api.contatos.service.impl;

import br.com.api.contatos.client.CepClient;
import br.com.api.contatos.error.exception.ObjectNotFoundException;
import br.com.api.contatos.model.CodigoPostal;
import br.com.api.contatos.repository.CodigoPostalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        final CodigoPostal codigoPostalMock = getCodigoPostal();

        Mockito.when(codigoPostalRepository.findById(cep)).thenReturn(Optional.of(codigoPostalMock));

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(cep);

        assertThat(codigoPostal).isNotNull().isEqualTo(codigoPostalMock);
    }

    @Test
    void buscarNovoCepComSucesso() {

        final String cep = "18682841";

        final CodigoPostal codigoPostalMock = getCodigoPostal();

        Mockito.when(codigoPostalRepository.findById(cep)).thenReturn(Optional.empty());
        Mockito.when(codigoPostalRepository.save(codigoPostalMock)).thenReturn(codigoPostalMock);
        Mockito.when(cepClient.buscarCep(cep)).thenReturn(getJson());

        final CodigoPostal codigoPostal = codigoPostalService.buscarCep(cep);

        assertThat(codigoPostal).isNotNull().isEqualTo(codigoPostalMock);
    }

    @Test
    void cepNaoEncotrado() {

        final String cep = "12345678";

        Mockito.when(codigoPostalRepository.findById(cep)).thenReturn(Optional.empty());
        Mockito.when(cepClient.buscarCep(cep)).thenThrow(WebClientResponseException.NotFound.class);

        assertThrows(ObjectNotFoundException.class, () -> codigoPostalService.buscarCep(cep));
    }

    private static CodigoPostal getCodigoPostal() {
        final CodigoPostal codigoPostalMock = new CodigoPostal();
        codigoPostalMock.setCep("18682841");
        codigoPostalMock.setJson(getJson());
        codigoPostalMock.setDataConsulta(LocalDateTime.now());
        return codigoPostalMock;
    }

    private static String getJson() {
        return "{\"cep\":\"18682-841\",\"logradouro\":\"Rua José Príncipe Penhafiel\",\"bairro\":\"Jardim Príncipe\",\"localidade\":\"Lençóis Paulista\",\"estado\":\"São Paulo\"}";
    }

}