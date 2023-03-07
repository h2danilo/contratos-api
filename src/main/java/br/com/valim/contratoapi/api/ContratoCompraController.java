package br.com.valim.contratoapi.api;

import br.com.valim.contratoapi.base.GenericController;
import br.com.valim.contratoapi.base.GenericService;
import br.com.valim.contratoapi.domain.contratocompra.ContratoCompraService;
import br.com.valim.contratoapi.domain.contratocompra.dtos.ContratoCompraDto;
import br.com.valim.contratoapi.entities.ContratoCompra;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contrato-compra")
@RequiredArgsConstructor
@Tag(name = "Contratos de Compra")
//@SecurityRequirement(name = "siagroapi")
public class ContratoCompraController  implements GenericController<ContratoCompra, ContratoCompraDto, Long> {

    private final ContratoCompraService service;

    @Override
    public ContratoCompraService getService() {
        return service;
    }

}
