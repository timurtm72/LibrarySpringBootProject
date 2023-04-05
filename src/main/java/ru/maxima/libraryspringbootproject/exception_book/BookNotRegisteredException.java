package ru.maxima.libraryspringbootproject.exception_book;

public class BookNotRegisteredException extends RuntimeException {
    public BookNotRegisteredException(String message) {
        super(message);
    }
}
