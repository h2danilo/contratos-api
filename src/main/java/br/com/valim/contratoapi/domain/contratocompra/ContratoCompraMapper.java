package br.com.valim.contratoapi.domain.contratocompra;

import br.com.valim.contratoapi.base.GenericMapper;
import br.com.valim.contratoapi.domain.contratocompra.dtos.ContratoCompraDto;
import br.com.valim.contratoapi.entities.ContratoCompra;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ContratoCompraMapper implements GenericMapper<ContratoCompra, ContratoCompraDto> {
    private final ModelMapper modelMapper;

    public ContratoCompraDto toDto(ContratoCompra contratoCompra) {
        return modelMapper.map(contratoCompra, ContratoCompraDto.class);
    }

    public ContratoCompra toEntity(ContratoCompraDto contratoCompraDto) {
        return modelMapper.map(contratoCompraDto, ContratoCompra.class);
    }

    @Override
    public void updateFromDto(ContratoCompraDto d, ContratoCompra contratoCompra) {
        modelMapper.map(d, contratoCompra);
    }


}
