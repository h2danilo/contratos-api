package br.com.valim.contratoapi.api;

import br.com.valim.contratoapi.responses.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.valim.contratoapi.exceptions.RecordNotFoundException;

@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFoundException(RecordNotFoundException e) {
        return new ApiErrorResponse("404", e.getMessage());
    }

    @ExceptionHandler(value = HibernateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleHibernateException(HibernateException ex) {
        return new ApiErrorResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                ex.getMessage());
    }

    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse bindException(BindException e) {
        return new ApiErrorResponse("500", e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleNullPointerException(NullPointerException e) {
        return new ApiErrorResponse("400", e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        return new ApiErrorResponse("400", e.getMessage());
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        return new ApiErrorResponse("400", e.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleIMissingRequestHeaderException(MissingRequestHeaderException e) {
        return new ApiErrorResponse("400", e.getMessage());
    }
}
