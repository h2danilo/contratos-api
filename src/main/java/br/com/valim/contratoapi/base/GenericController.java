package br.com.valim.contratoapi.base;

import br.com.valim.contratoapi.entities.BaseEntity;
import br.com.valim.contratoapi.utils.ParseFilial;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.security.Principal;

public interface GenericController<T extends BaseEntity, DTO, ID> {
    GenericService<T, DTO, ID> getService();

    @GetMapping
    default Page<?> findAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order", required = false) String order,
            HttpServletRequest request
    ) {
        Long filial = ParseFilial.parseHeader(request);

        return getService().findAll(filial, page, pageSize, order, request);
    }

    @GetMapping("{id}")
    default DTO findOne(@PathVariable("id") ID id) {
        return getService().findOne(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    default DTO create(@Valid @RequestBody DTO dto, HttpServletRequest request) {
        Long filial = ParseFilial.parseHeader(request);
        return getService().create(filial, dto, request);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    default void update(@NotNull @PathVariable("id") ID id, @Valid @RequestBody DTO dto, Principal user) {
        getService().update(id, dto, user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    default void delete(@NotNull @PathVariable("id") ID id, HttpServletRequest request) {
        getService().delete(id, request);
    }
}
