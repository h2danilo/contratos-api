package br.com.valim.contratoapi.domain.unidademedida;

import br.com.valim.contratoapi.entities.UnidadeMedida;

public interface UnidadeMedidaRepository extends org.springframework.data.jpa.repository.JpaRepository<UnidadeMedida, String>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<UnidadeMedida> {
}
