package br.com.valim.contratoapi.domain.contratocompra.dtos;

import br.com.valim.contratoapi.enums.TipoFreteEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoCompraDto {

    private Long filialId;

    private Long id;

    @Size(max = 9, message = "Número do contrato de compra deve ter no máximo 9 caracteres")
    private String numero;

    private String sequencia;

    @Size(max = 10, message = "Complemento do contrato de compra deve ter no máximo 10 caracteres")
    private String complemento;

    @NotNull(message = "Data de emissão do contrato de compra é obrigatório")
    private LocalDate emissao;

    @NotNull(message = "Data de início de entrega do contrato de compra é obrigatório")
    private LocalDate dataInicioEntrega;

    @NotNull(message = "Data de término de entrega do contrato de compra é obrigatório")
    private LocalDate dataTerminoEntrega;

    @PositiveOrZero(message = "Valor da comissão do corretor é obrigatório")
    private BigDecimal comissaoCorretor;

    @PositiveOrZero(message = "Valor do frete do contrato de compra é obrigatório")
    private BigDecimal quantidade;

    @NotNull(message = "Unidade de medida do contrato de compra é obrigatório")
    @JsonProperty("unidadeMedidaCodigo")
    private String unidadeMedidaId;

    @JsonProperty("contratoMinutaId")
    private String contratoMinutaId;

    @PositiveOrZero(message = "Valor do produto do contrato de compra é obrigatório")
    private BigDecimal preco;

    @NotNull(message = "Tipo do frete do contrato de compra é obrigatório")
    private TipoFreteEnum tipoFrete;

    @Size(max = 1000, message = "Observação do contrato de compra deve ter no máximo 1000 caracteres")
    private String observacoes;

    private BigDecimal saldo;

    private String aprovadoPor;

    private String aprovadoEm;

    private String liberadoPor;

    private String liberadoEm;

    private Long solicitacaoContratoId;

    private BigDecimal quantidade2;

    @JsonProperty("unidadeMedidaCodigo2")
    private String unidadeMedida2Id;

    private BigDecimal preco2;

    private String bolsaReferenciaId;

    @NotNull(message = "O campo data de pagamento é obrigatório.")
    private LocalDate dataPagamento;

    private String contratoPdfBase64;

    private String docuSignDocumentId;
}
