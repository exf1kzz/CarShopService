package ru.malov.domain.exeption;

public class DomainValidationException extends RuntimeException {
    public DomainValidationException(String message) {
        super(message);
    }
}
