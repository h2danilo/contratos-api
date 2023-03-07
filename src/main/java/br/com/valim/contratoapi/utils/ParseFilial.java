package br.com.valim.contratoapi.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ParseFilial {
    private ParseFilial() {

    }

    public static Long parseHeader(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("filial"))
                ? Long.parseLong(request.getHeader("filial"))
                : null;
    }
}
