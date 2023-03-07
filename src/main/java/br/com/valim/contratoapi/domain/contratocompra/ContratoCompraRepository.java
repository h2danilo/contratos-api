package br.com.valim.contratoapi.domain.contratocompra;

import br.com.valim.contratoapi.entities.ContratoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContratoCompraRepository extends JpaRepository<ContratoCompra, Long>, JpaSpecificationExecutor<ContratoCompra> {
    @Query(value = "SELECT MAX(numero) FROM contratos_compra  WHERE filial_id = :filialId", nativeQuery = true)
    String getUltimoNumero(@Param("filialId") Long filialId);
}
