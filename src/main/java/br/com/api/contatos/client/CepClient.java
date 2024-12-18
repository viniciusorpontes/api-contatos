package br.com.api.contatos.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CepClient {

    public static final String URL = "http://localhost:8090/ws";
    public static final String REQUEST_PARAM = "/%s/json/";

    private final WebClient webClient;

    public CepClient() {
        this.webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String buscarCep(String cep) {
        final String urlPath = String.format(REQUEST_PARAM, cep);
        return webClient.get()
                .uri(urlPath)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
