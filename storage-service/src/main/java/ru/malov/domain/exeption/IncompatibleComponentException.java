package ru.malov.domain.exeption;

public class IncompatibleComponentException extends RuntimeException {
    public IncompatibleComponentException(String message) {
        super(message);
    }
}
