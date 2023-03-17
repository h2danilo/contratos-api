package br.com.valim.contratoapi.domain.unidademedida;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeMedidaDto {
    private Long filialId;

    @NotBlank(message = "Código da Unidade de Medida é obrigatório")
    @Size(max = 4, message = "Código da Unidade de Medida deve conter no máximo 4 caracteres")
    @JsonProperty("codigo")
    private String id;

    @NotBlank(message = "Descrição da Unidade de Medida é obrigatório")
    @Size(max = 100, message = "Descrição da Unidade de Medida deve conter no máximo 100 caracteres")
    private String descricao;

    private String chaveIntegracao;
}
