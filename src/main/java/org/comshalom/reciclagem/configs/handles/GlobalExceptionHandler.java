package org.comshalom.reciclagem.configs.handles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage;

        if (fieldError != null) {
            String fieldName = fieldError.getField();
            errorMessage = "Erro de validação no campo '" + fieldName + "': " + fieldError.getDefaultMessage();
        } else {
            errorMessage = "Erro de validação: Um ou mais campos contêm valores inválidos.";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
