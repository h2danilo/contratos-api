package br.com.valim.contratoapi.domain.contratocompra.utils;

import br.com.valim.contratoapi.domain.contratocompra.ContratoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoCompraNumeracao {
    private final ContratoCompraRepository repository;

    public String getProximoNumero(Long filialId) {
        String ultimoNumero = repository.getUltimoNumero(filialId);
        Integer proximoNumero = StringUtils.isEmpty(ultimoNumero) ? 1 : Integer.parseInt(ultimoNumero) + 1;

        return StringUtils.leftPad(proximoNumero.toString(), 8, "0");
    }
}
