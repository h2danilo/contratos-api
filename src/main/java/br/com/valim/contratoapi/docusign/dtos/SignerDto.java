package br.com.valim.contratoapi.docusign.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignerDto {
    @NotNull(message = "email é obrigatório")
    private String email;

    @NotNull(message = "nome é obrigatório")
    private String name;
}
