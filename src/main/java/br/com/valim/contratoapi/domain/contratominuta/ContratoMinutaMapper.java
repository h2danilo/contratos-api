package br.com.valim.contratoapi.domain.contratominuta;

import br.com.valim.contratoapi.base.GenericMapper;
import br.com.valim.contratoapi.entities.ContratoMinuta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContratoMinutaMapper implements GenericMapper<ContratoMinuta, ContratoMinutaDto> {
    private final ModelMapper modelMapper;

    @Override
    public ContratoMinutaDto toDto(ContratoMinuta t) {
        return modelMapper.map(t, ContratoMinutaDto.class);
    }

    @Override
    public ContratoMinuta toEntity(ContratoMinutaDto d) {
        return modelMapper.map(d, ContratoMinuta.class);
    }

    @Override
    public void updateFromDto(ContratoMinutaDto d, ContratoMinuta contratoMinuta) {
        modelMapper.map(d, contratoMinuta);
    }
}
