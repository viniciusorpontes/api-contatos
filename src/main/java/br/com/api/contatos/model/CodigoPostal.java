package br.com.api.contatos.model;

import br.com.api.contatos.dto.CepDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "codigo_postal")
public class CodigoPostal {

    public CodigoPostal(String response) {
        try {
            final CepDTO cepDTO = new ObjectMapper().readValue(response, CepDTO.class);
            this.cep = cepDTO.cep();
            this.logradouro = cepDTO.logradouro();
            this.bairro = cepDTO.bairro();
            this.cidade = cepDTO.localidade();
            this.estado = cepDTO.estado();
            this.json = response;
            this.dataConsulta = LocalDateTime.now();
        } catch (JsonProcessingException e) {
            //TODO MELHORAR TRATAMENTO DE EXCEPTION
            throw new RuntimeException(e);
        }
    }

    @Id
    @Column(name = "cep", nullable = false, length = 20)
    private String cep;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "bairro", nullable = false, length = 30)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 30)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "json", nullable = false)
    private String json;

    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CodigoPostal codigoPostal = (CodigoPostal) object;
        return this.cep.equals(codigoPostal.cep);
    }

    @Override
    public int hashCode() {
        return cep.hashCode();
    }

}
