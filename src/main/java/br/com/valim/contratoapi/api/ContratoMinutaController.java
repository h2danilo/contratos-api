package br.com.valim.contratoapi.api;

import br.com.valim.contratoapi.base.GenericController;
import br.com.valim.contratoapi.base.GenericService;
import br.com.valim.contratoapi.domain.contratominuta.ContratoMinutaDto;
import br.com.valim.contratoapi.domain.contratominuta.ContratoMinutaParamsDto;
import br.com.valim.contratoapi.domain.contratominuta.ContratoMinutaService;
import br.com.valim.contratoapi.entities.ContratoMinuta;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contrato-minuta")
@RequiredArgsConstructor
@Tag(name = "Minutas de Contrato")
public class ContratoMinutaController implements GenericController<ContratoMinuta, ContratoMinutaDto, Long> {

    private final ContratoMinutaService serviceMinuta;

    @Override
    public GenericService<ContratoMinuta, ContratoMinutaDto, Long> getService() {
        return serviceMinuta;
    }

    @GetMapping("minuta-params")
    public List<ContratoMinutaParamsDto> findAllMinutaParams() {
        return serviceMinuta.findAllMinutaParams();
    }
}
