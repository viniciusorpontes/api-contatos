package br.com.api.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContatoSalvarAlterarDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Formato do telefone inválido. Use (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
        @Size(min = 8, max = 20, message = "O telefone deve ter entre 8 e 20 caracteres")
        String telefone,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato do email inválido")
        @Size(min = 5, max = 100, message = "O email deve ter entre 5 e 100 caracteres")
        String email,

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato do CEP inválido. Use XXXXX-XXX")
        @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres")
        String cep,

        @NotBlank(message = "O número é obrigatório")
        @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres")
        String numero
) {

}
