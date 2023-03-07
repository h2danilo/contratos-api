package br.com.valim.contratoapi.base;

import br.com.valim.contratoapi.entities.BaseEntity;
import br.com.valim.contratoapi.exceptions.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

public interface GenericService <T extends BaseEntity, DTO, ID> {

    JpaRepository<T, ID> getRepository();

    Class<T> getEntityClass();

    GenericMapper<T, DTO> getMapper();

    //FilialRepository getFilialRepository();

    default String getParameter(HttpServletRequest request, String queryParam) {
        return request.getParameter(queryParam);
    }

    default DTO findOne(ID id) {
        return getRepository().findById(id)
                .map(t -> getMapper().toDto(t))
                .orElseThrow(RecordNotFoundException::new);
    }

    default DTO create(Long filialId, DTO dto, HttpServletRequest request) {
        //Filial filial = getFilialRepository().findById(filialId).orElse(null);

        T entity = getMapper().toEntity(dto);
        //entity.setFilial(filial);

        return getMapper().toDto(getRepository().save(entity));
    }

    default void update(ID id, DTO dto, Principal user) {
        T t = getMapper().toEntity(
                getMapper().toDto(
                        getRepository().findById(id)
                                .orElseThrow(RecordNotFoundException::new)));

        getMapper().updateFromDto(dto, t);
        var userName = user != null ? user.getName() : "";

        t.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        t.setUpdatedBy(userName == null ? "" : userName);

        getRepository().save(t);
    }

    default void delete(ID id, HttpServletRequest request) {
        getRepository()
                .delete(getRepository().findById(id)
                        .orElseThrow(RecordNotFoundException::new));
    }

    default Page<?> findAll(Long filial, Integer page, Integer pageSize, String order, HttpServletRequest request) {
        String search = getParameter(request, "search");
        String filter = getParameter(request, "filter");

        Pageable pageable = order(page, pageSize, order);

        return filter(filial, search, filter, pageable, request);
    }

    default Pageable order(Integer page, Integer pageSize, String order) {
        Sort sort;
        Pageable pageable;

        if (order != null) {
            sort = Sort.by(order.trim().startsWith("-")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC, order.replace("-", ""));
            pageable = PageRequest.of(page - 1, pageSize).withSort(sort);
        } else {
            sort = Sort.by(Sort.Direction.ASC, "id");
            pageable = PageRequest.of(page - 1, pageSize, sort);
        }
        return pageable;
    }

    default Page<?> filter(Long filial, String search, String filter, Pageable pageable, HttpServletRequest request) {

        return getRepository().findAll(pageable);
    }
}
