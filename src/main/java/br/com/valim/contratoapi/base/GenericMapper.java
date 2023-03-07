package br.com.valim.contratoapi.base;

public interface GenericMapper<T, DTO>  {
    DTO toDto(T t);

    T toEntity(DTO d);

    void updateFromDto(DTO d, T t);
}
