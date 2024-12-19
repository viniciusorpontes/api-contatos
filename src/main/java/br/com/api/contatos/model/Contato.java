package br.com.api.contatos.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "cep")
    private CodigoPostal codigoPostal;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Contato contato = (Contato) object;
        return id.equals(contato.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
