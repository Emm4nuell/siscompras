package br.com.siscompras.exception;

import br.com.siscompras.exception.ErrorException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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
}
