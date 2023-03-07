package br.com.valim.contratoapi.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ApiErrorResponse {
    private String code;

    private String message;

    private List<Map<String, String>> details;

    private String detailedMessage;

    public ApiErrorResponse(String code, String message) {
        this(code, message, new ArrayList<>());
    }

    public ApiErrorResponse(String code, String message, List<Map<String, String>> details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public ApiErrorResponse(String code, String message, String detailedMessage, List<Map<String, String>> details ) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.detailedMessage = detailedMessage;
    }
}
