package ru.maxima.libraryspringbootproject.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class SearchBook {
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2,max = 50,message = "Название книги не может быть не менее 2 символов и не более 50 символов")
    private String name;
    private Integer yearOfProduction;
    private String author;

    public SearchBook() {
    }

    public SearchBook(String name, Integer yearOfProduction, String author) {
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchBook that = (SearchBook) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(yearOfProduction, that.yearOfProduction))
            return false;
        return Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (yearOfProduction != null ? yearOfProduction.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
