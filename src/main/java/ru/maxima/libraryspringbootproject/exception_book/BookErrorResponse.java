package ru.maxima.libraryspringbootproject.exception_book;

import java.util.Date;

public class BookErrorResponse {
    private String message;
    private Date timestamp;

    public BookErrorResponse(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
