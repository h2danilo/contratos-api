package br.com.valim.contratoapi.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "chave_integracao", length = 100, nullable = true)
    private String chaveIntegracao;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private String updatedAt;
}
