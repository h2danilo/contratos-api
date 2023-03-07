package br.com.valim.contratoapi.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private String key;
    private Object value;
    private Object value2;
    private SearchOperation operation;

    private String joinProperty;

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public SearchCriteria(String key, Object value, Object value2, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.value2 = value2;
        this.operation = operation;
    }
}
