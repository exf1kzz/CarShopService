package ru.malov.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.malov.domain.exeption.DomainValidationException;
import ru.malov.domain.exeption.EntityNotFoundException;
import ru.malov.domain.exeption.StorageUnavailableException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<String> handleValidation(DomainValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(StorageUnavailableException.class)
    public ResponseEntity<String> handleStorageUnavailable(StorageUnavailableException ex) {
        return ResponseEntity.status(503).body(ex.getMessage());
    }
}
