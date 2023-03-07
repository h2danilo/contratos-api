package br.com.valim.contratoapi.domain.contratominuta;

import br.com.valim.contratoapi.entities.ContratoMinuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContratoMinutaRepository extends JpaRepository<ContratoMinuta, Long>, JpaSpecificationExecutor<ContratoMinuta> {

}
