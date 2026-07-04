package ru.malov.domain.exeption;

public class StorageUnavailableException extends RuntimeException {
    public StorageUnavailableException(String message, Throwable cause) {
      super(message, cause);
    }
}
