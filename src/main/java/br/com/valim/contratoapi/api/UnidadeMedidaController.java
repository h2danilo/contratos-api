package br.com.valim.contratoapi.api;

import br.com.valim.contratoapi.base.GenericController;
import br.com.valim.contratoapi.domain.contratocompra.dtos.ContratoCompraDto;
import br.com.valim.contratoapi.domain.unidademedida.UnidadeMedidaDto;
import br.com.valim.contratoapi.domain.unidademedida.UnidadeMedidaService;
import br.com.valim.contratoapi.entities.ContratoCompra;
import br.com.valim.contratoapi.entities.UnidadeMedida;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unidade-medida")
@RequiredArgsConstructor
@Tag(name = "Unidade de Medida")
public class UnidadeMedidaController implements GenericController<UnidadeMedida, UnidadeMedidaDto, String> {
    private final UnidadeMedidaService service;

    @Override
    public UnidadeMedidaService getService() {
        return service;
    }
}