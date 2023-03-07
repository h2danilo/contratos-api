package br.com.valim.contratoapi.responses;

import lombok.Data;

import java.util.List;

@Data
public class ApiPageResponse<T>  {
    List<T> items;
    Boolean hasNext;
}
