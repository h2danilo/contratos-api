package br.com.valim.contratoapi.domain.contratocompra;

import br.com.valim.contratoapi.base.GenericMapper;
import br.com.valim.contratoapi.base.GenericService;
import br.com.valim.contratoapi.config.EnvironmentConfig;
import br.com.valim.contratoapi.docusign.DSEnvelope;
import br.com.valim.contratoapi.docusign.dtos.SignerDto;
import br.com.valim.contratoapi.domain.contratocompra.dtos.ContratoCompraDto;
import br.com.valim.contratoapi.domain.contratocompra.dtos.ContratoCompraMinutaDto;
import br.com.valim.contratoapi.domain.contratocompra.utils.ContratoCompraNumeracao;
import br.com.valim.contratoapi.domain.contratominuta.ContratoMinutaRepository;
import br.com.valim.contratoapi.entities.ContratoCompra;
import br.com.valim.contratoapi.entities.ContratoMinuta;
import br.com.valim.contratoapi.exceptions.ContratoMinutaException;
import br.com.valim.contratoapi.exceptions.RecordNotFoundException;
import br.com.valim.contratoapi.response.ResponseDefault;
import br.com.valim.contratoapi.utils.HandlePdf;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
@AllArgsConstructor
public class ContratoCompraService implements GenericService<ContratoCompra, ContratoCompraDto, Long> {

    private final ContratoCompraMapper mapper;

    private final ContratoMinutaRepository repositoryContratoMinuta;

    private final ContratoCompraRepository repository;

    private final ModelMapper modelMapper;

    private final EnvironmentConfig docusignBasePath;

    private final ContratoCompraNumeracao numeracao;

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

    @Override
    public ContratoCompraDto create(Long filialId , ContratoCompraDto dto, HttpServletRequest request) {
        var t = mapper.toEntity(dto);
        t.setSaldo(dto.getSaldo());
        t.setNumero(numeracao.getProximoNumero(filialId == null ? 1 : filialId ));
        t.setSequencia("01");
        t.setCreatedAt(String.valueOf(LocalDateTime.now()));

        if (request.getUserPrincipal() != null){
            t.setAprovadoEm(LocalDate.now().toString());
            t.setAprovadoPor(request.getUserPrincipal().getName());
            t.setLiberadoEm(LocalDate.now().toString());
            t.setLiberadoPor(request.getUserPrincipal().getName());
        }

        return mapper.toDto(repository.save(t));
    }

    @Override
    @Transactional
    public void update(Long id, ContratoCompraDto dto, Principal user) {
        ContratoCompraDto contratoDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(RecordNotFoundException::new);

        modelMapper.map(dto, contratoDto);

        ContratoCompra t = mapper.toEntity(contratoDto);

        t.setSequencia("01");
        //t.setUpdatedBy(user.getName());

        t.setUpdatedAt(String.valueOf(LocalDateTime.now()));

        repository.save(t);
    }

    public ContratoCompraMinutaDto doContract(Long id) throws Exception {

        ContratoCompraDto contratoDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(RecordNotFoundException::new);

        String minutaId = contratoDto.getContratoMinutaId();
        ContratoMinuta contratoMinuta = repositoryContratoMinuta.findById(Long.parseLong(minutaId))
                .orElseThrow(() -> new ContratoMinutaException("Não foi possível carregar Minuta do Contrato para gerar o Contrato"));

        ContratoCompraMinutaDto contratoMinutaDto = new ContratoCompraMinutaDto();
        contratoMinutaDto.setId(contratoDto.getId());
        contratoMinutaDto.setEmissao(contratoDto.getEmissao());
        contratoMinutaDto.setNumero(contratoDto.getNumero());
        contratoMinutaDto.setContratoMinutaId((contratoDto.getContratoMinutaId()));
        contratoMinutaDto.setMinutaContrato(contratoMinuta.getMinuta());

        String minuta = contratoMinuta.getMinuta();

        minuta = minuta.replaceAll("#valor_saca_contrato#", contratoDto.getPreco().toString());
        minuta = minuta.replaceAll("#num_contrato#", contratoDto.getNumero().toString());

        contratoMinutaDto.setMinutaContrato(minuta);

        return contratoMinutaDto;
    }

    public ResponseDefault sendContract(Long id, String contrato) throws Exception {
        if (contrato.isEmpty())
            throw new ContratoMinutaException("Contrato inválido!");

        ContratoCompraDto contratoDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(RecordNotFoundException::new);


        //Converter HTML em PDF
        byte[] pdfBytes = HandlePdf.createPdfFromHtml(contrato);
        //Converter PDF em Base 64
        String base64 = org.apache.commons.codec.binary.Base64.encodeBase64String(pdfBytes);

        contratoDto.setContratoPdfBase64(base64);

        ArrayList<SignerDto> signersDto = new ArrayList<SignerDto>();

        signersDto.add(new SignerDto("h2danilovalim@gmail.com", "Danilo") );
        signersDto.add(new SignerDto("piheceb193@necktai.com", "Danilo necktai") );
        signersDto.add(new SignerDto("0c9d151193@boxmail.lol", "Danilo boxmail") );

        //"Contrato_"+(contratoDto.getFilial_id() != null ? contratoDto.getFilial_id()+"_"+contratoDto.getId() : Long.toString(contratoDto.getId())),
        String docuSignDocumentId = new DSEnvelope(docusignBasePath).createEnvelope(
                signersDto,
                (Long.toString(contratoDto.getId())),
                "Contrato_"+(contratoDto.getId()),
                base64
        );

        contratoDto.setDocuSignDocumentId(docuSignDocumentId);

        ContratoCompra t = mapper.toEntity(contratoDto);
        t.setUpdatedBy(String.valueOf(LocalDateTime.now()));
        repository.save(t);

        return new ResponseDefault("200", docuSignDocumentId);
    }
}
