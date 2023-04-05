package ru.maxima.libraryspringbootproject.exception_book;

public class BookNotUpdatedException extends RuntimeException {
    public BookNotUpdatedException(String message) {
        super(message);
    }
}
