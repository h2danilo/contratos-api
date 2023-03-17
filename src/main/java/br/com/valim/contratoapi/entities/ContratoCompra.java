package br.com.valim.contratoapi.entities;

import br.com.valim.contratoapi.enums.TipoFreteEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "contrato_compra")
public class ContratoCompra extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    private String numero;

    @Column(nullable = true, length = 10)
    private String complemento;

    @Column(nullable = false, length = 2)
    private String sequencia;

    @Column(nullable = false)
    private LocalDate emissao;

    @Column(name = "data_inicio_entrega", nullable = false)
    private LocalDate dataInicioEntrega;

    @Column(name = "data_termino_entrega", nullable = false)
    private LocalDate dataTerminoEntrega;

    @Column(name="comissao_corretor")
    private BigDecimal comissaoCorretor;

    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "contrato_minuta_id", nullable = false)
    private ContratoMinuta contratoMinuta;

    @ManyToOne
    @JoinColumn(name = "unidade_medida_codigo", nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoFreteEnum tipoFrete;

    @Column(length = 1000)
    private String observacoes;

    @Column
    private BigDecimal saldo;

    @Column(name = "aprovado_por")
    private String aprovadoPor;

    @Column(name = "aprovado_em")
    private String aprovadoEm;

    @Column(name = "liberado_por")
    private String liberadoPor;

    @Column(name = "liberado_em")
    private String liberadoEm;

    @Column(name = "quantidade_2")
    private BigDecimal quantidade2;


    @Column(name = "preco_2", precision = 12, scale = 2)
    private BigDecimal preco2;


    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "contrato_pdf", columnDefinition = "TEXT")
    private String contratoPdfBase64;

    @Column(name = "docusign_document_id")
    private String docuSignDocumentId;
}
