package ru.maxima.libraryspringbootproject.exception_person;

public class PersonNotUpdatedException  extends RuntimeException {
    public PersonNotUpdatedException(String message) {
        super(message);
    }
}
