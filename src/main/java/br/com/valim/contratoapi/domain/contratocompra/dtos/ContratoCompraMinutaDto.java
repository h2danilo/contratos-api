package br.com.valim.contratoapi.domain.contratocompra.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoCompraMinutaDto {
    private Long id;

    @Size(max = 9, message = "Número do contrato de compra deve ter no máximo 9 caracteres")
    private String numero;

    @NotNull(message = "Data de emissão do contrato de compra é obrigatório")
    private LocalDate emissao;

    @JsonProperty("contratoMinutaId")
    private String contratoMinutaId;

    private String minutaContrato;

}
