package br.com.siscompras.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorException {

    private String error;
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;
    List<ValidException> valid;

}
