package br.com.valim.contratoapi.domain.unidademedida;

import br.com.valim.contratoapi.base.GenericService;
import br.com.valim.contratoapi.entities.UnidadeMedida;
import br.com.valim.contratoapi.utils.GenericSpecification;
import br.com.valim.contratoapi.utils.SearchCriteria;
import br.com.valim.contratoapi.utils.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class UnidadeMedidaService implements GenericService<UnidadeMedida, UnidadeMedidaDto, String> {
    private final UnidadeMedidaRepository repository;

    private final UnidadeMedidaMapper mapper;

    //private final FilialRepository filialRepository;

//    @Override
//    public FilialRepository getFilialRepository() {
//        return filialRepository;
//    }

    @Override
    public Class<UnidadeMedida> getEntityClass() {
        return UnidadeMedida.class;
    }

    @Override
    public UnidadeMedidaRepository getRepository() {
        return repository;
    }

    @Override
    public UnidadeMedidaMapper getMapper() {
        return mapper;
    }

    @Override
    public Page<UnidadeMedidaDto> filter(Long filialId, String search, String filter, Pageable pageable, HttpServletRequest request) {
        String descricao = request.getParameter("descricao");
        String codigo = request.getParameter("codigo");

        var genericSpecification = new GenericSpecification<UnidadeMedida>();

        var searchable = filter != null ? filter : search;
        if (searchable != null) {
            searchable = searchable.isEmpty() || searchable.isBlank() ? null : searchable;
        }

        if (searchable != null || descricao != null) {
            genericSpecification
                    .add(new SearchCriteria("descricao", searchable != null ? searchable : descricao, SearchOperation.CONTAINS));
        }

        if (codigo != null) {
            genericSpecification.add(new SearchCriteria("codigo", codigo, SearchOperation.EQUAL));
        }

        return repository.findAll(genericSpecification, pageable).map(mapper::toDto);
    }
}
