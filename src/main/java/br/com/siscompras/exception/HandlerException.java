package br.com.siscompras.exception;

import br.com.siscompras.exception.ErrorException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorException> nullPointerException(NullPointerException exception, HttpServletRequest http){
        ErrorException error = new ErrorException(
                "Internal Server Error...",
                exception.getMessage(),
                http.getRequestURI().toString(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest http){

        List<FieldError> field = exception.getBindingResult().getFieldErrors();
        List<ValidException> valid = field.stream()
                .map((e) -> new ValidException(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());


        ErrorException error = new ErrorException(
                "Validation Error",
                "Validation Error",
                http.getRequestURI().toString(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                valid
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorException> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, HttpServletRequest http){
        ErrorException error = new ErrorException(
                "Internal Error Server",
                "Internal Error Server",
                http.getRequestURI().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorException> dataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest http){
        ErrorException error = new ErrorException(
                "Internal Error Server",
                "Dados duplicado no sistema!",
                http.getRequestURI().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
