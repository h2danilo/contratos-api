package br.com.valim.contratoapi.domain.contratocompra;

import br.com.valim.contratoapi.base.GenericMapper;
import br.com.valim.contratoapi.base.GenericService;
import br.com.valim.contratoapi.domain.contratocompra.dtos.ContratoCompraDto;
import br.com.valim.contratoapi.entities.ContratoCompra;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContratoCompraService implements GenericService<ContratoCompra, ContratoCompraDto, Long> {

    private final ContratoCompraMapper mapper;

    private final ContratoCompraRepository repository;

    //private final ModelMapper modelMapper;

    @Override
    public Class<ContratoCompra> getEntityClass() {
        return ContratoCompra.class;
    }

    @Override
    public JpaRepository<ContratoCompra, Long> getRepository() {
        return repository;
    }

    @Override
    public GenericMapper<ContratoCompra, ContratoCompraDto> getMapper() {
        return mapper;
    }
}
