package br.com.valim.contratoapi.domain.contratominuta;

import br.com.valim.contratoapi.base.GenericMapper;
import br.com.valim.contratoapi.base.GenericService;
import br.com.valim.contratoapi.entities.ContratoMinuta;
import br.com.valim.contratoapi.utils.GenericSpecification;
import br.com.valim.contratoapi.utils.SearchCriteria;
import br.com.valim.contratoapi.utils.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ContratoMinutaService implements GenericService<ContratoMinuta, ContratoMinutaDto, Long> {

    private final ContratoMinutaMapper mapper;

    private final ContratoMinutaRepository repository;

    //private final FilialRepository filialRepository;


    @Override
    public JpaRepository<ContratoMinuta, Long> getRepository() {
        return repository;
    }

    @Override
    public Class<ContratoMinuta> getEntityClass() {
        return ContratoMinuta.class;
    }

    @Override
    public GenericMapper<ContratoMinuta, ContratoMinutaDto> getMapper() {
        return mapper;
    }

    @Override
    public Page<ContratoMinutaDto> filter(Long filial, String search, String filter, Pageable pageable,
                                       HttpServletRequest request) {

        var descricao = request.getParameter("descricao");
        //var codigo = request.getParameter("codigo");

        var genericSpecification = new GenericSpecification<ContratoMinuta>();
        //genericSpecification.add(new SearchCriteria("filial", filial, SearchOperation.EQUAL));

        var searchable = filter != null ? filter : search;
        if (searchable != null) {
            searchable = searchable.isEmpty() || searchable.isBlank() ? null : searchable;
        }

        if (searchable != null || descricao != null) {
            genericSpecification
                    .add(new SearchCriteria("descricao", searchable != null ? searchable : descricao,
                            SearchOperation.CONTAINS));
        }

        /*if (codigo != null) {
            genericSpecification.add(new SearchCriteria("codigo", codigo, SearchOperation.EQUAL));
        }*/

        var response = repository.findAll(genericSpecification, pageable);
        var responseDto = response
                .map(mapper::toDto);
        return responseDto;
    }

    public List<ContratoMinutaParamsDto> findAllMinutaParams() {

        List<ContratoMinutaParamsDto> params = new ArrayList<ContratoMinutaParamsDto>();
        params.add(new ContratoMinutaParamsDto("num_contrato", "Contrato - Nº"));
        params.add(new ContratoMinutaParamsDto("data_contrato", "Contrato - Data"));
        params.add(new ContratoMinutaParamsDto("dia_contrato", "Contrato - Dia"));
        params.add(new ContratoMinutaParamsDto("mes_contrato", "Contrato - Mês"));
        params.add(new ContratoMinutaParamsDto("ano_contrato", "Contrato - Ano"));
        params.add(new ContratoMinutaParamsDto("valor_saca_contrato", "Contrato - Vl.Saca"));
        params.add(new ContratoMinutaParamsDto("total_contrato", "Contrato - Total"));
        params.add(new ContratoMinutaParamsDto("vencto_contrato", "Contrato - Vencto"));
        params.add(new ContratoMinutaParamsDto("qtde_contrato", "Contrato - Qtde"));
        params.add(new ContratoMinutaParamsDto("prazo_max_contrato", "Contrato - Prazo Mín."));
        params.add(new ContratoMinutaParamsDto("prazo_min_contrato", "Contrato - Prazo Max."));
        params.add(new ContratoMinutaParamsDto("armazem_contrato", "Contrato - Armazem"));

        params.add(new ContratoMinutaParamsDto("nome_vendedor", "Vendedor - Nome"));
        params.add(new ContratoMinutaParamsDto("cnpj_vendedor", "Vendedor - Cnpj"));
        params.add(new ContratoMinutaParamsDto("ie_vendedor", "Vendedor - IE"));
        params.add(new ContratoMinutaParamsDto("endereco_vendedor", "Vendedor - Endereço"));
        params.add(new ContratoMinutaParamsDto("bairro_vendedor", "Vendedor - Bairro"));
        params.add(new ContratoMinutaParamsDto("cidade_vendedor", "Vendedor - Cidade"));
        params.add(new ContratoMinutaParamsDto("uf_vendedor", "Vendedor - Uf"));
        params.add(new ContratoMinutaParamsDto("cep_vendedor", "Vendedor - Cep"));

        params.add(new ContratoMinutaParamsDto("descricao_safra", "Safra - Descrição"));

        return params;
    }


}
