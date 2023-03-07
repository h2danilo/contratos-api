package br.com.valim.contratoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unidade_medida")
@Data
@EqualsAndHashCode(callSuper = false)
public class UnidadeMedida extends BaseEntity {

    @Id
    @Column(name = "codigo", length = 4, nullable = false)
    private String id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "chave_integracao", length = 100, nullable = true)
    private String chaveIntegracao;

}