package br.com.valim.contratoapi.domain.contratominuta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContratoMinutaDto {

    private Long id;

    @NotEmpty(message = "Descrição é obrigatório.")
    @Size(max = 150)
    private String descricao;

    private Boolean inativo = false;

    private Long filial_id;

    private String minuta;

}
