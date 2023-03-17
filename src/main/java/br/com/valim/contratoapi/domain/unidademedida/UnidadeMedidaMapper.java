package br.com.valim.contratoapi.domain.unidademedida;

import br.com.valim.contratoapi.base.GenericMapper;
import br.com.valim.contratoapi.entities.UnidadeMedida;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnidadeMedidaMapper implements GenericMapper<UnidadeMedida, UnidadeMedidaDto> {
    private final ModelMapper modelMapper;

    public UnidadeMedidaDto toDto(UnidadeMedida unidadeMedida) {
        return modelMapper.map(unidadeMedida, UnidadeMedidaDto.class);
    }

    public UnidadeMedida toEntity(UnidadeMedidaDto unidadeMedidaDto) {
        return modelMapper.map(unidadeMedidaDto, UnidadeMedida.class);
    }

    @Override
    public void updateFromDto(UnidadeMedidaDto d, UnidadeMedida unidadeMedida) {
        modelMapper.map(d, unidadeMedida);
    }
}
