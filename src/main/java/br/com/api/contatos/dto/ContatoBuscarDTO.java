package br.com.api.contatos.dto;

import br.com.api.contatos.model.Contato;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoBuscarDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public static ContatoBuscarDTO toDTO(Contato contato) {

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = mapper.readTree(contato.getCodigoPostal().getJson());
        } catch (JsonProcessingException e) {
            //TODO Dúvida com o Jorjão
            throw new RuntimeException(e);
        }

        final ContatoBuscarDTO dto = new ContatoBuscarDTO();

        dto.setId(contato.getId());
        dto.setNome(contato.getNome());
        dto.setTelefone(contato.getTelefone());
        dto.setEmail(contato.getEmail());
        dto.setCep(contato.getCodigoPostal().getCep());
        dto.setEndereco(jsonNode.get("logradouro").asText());
        dto.setNumero(contato.getNumero());
        dto.setBairro(jsonNode.get("bairro").asText());
        dto.setCidade(jsonNode.get("localidade").asText());
        dto.setEstado(jsonNode.get("estado").asText());

        return dto;
    }


}
