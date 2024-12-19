package br.com.api.contatos.model;

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

    public CodigoPostal(String cep, String json) {
        this.cep = cep;
        this.json = json;
        this.dataConsulta = LocalDateTime.now();
    }

    @Id
    @Column(name = "cep", nullable = false, length = 20)
    private String cep;

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
