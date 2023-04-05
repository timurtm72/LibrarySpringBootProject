package ru.maxima.libraryspringbootproject.dto;

import jakarta.persistence.*;


import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
@Component
public class BookDTO {
    private Long Id;
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2,max = 50,message = "Название книги не может быть не менее 2 символов и не более 50 символов")
    @Column(name = "name")
    private String name;
    @Min(value = 1900,message = "Год издания книги должен быть больше 1900 года")
    @Column(name = "year_of_production")
    private Integer yearOfProduction;
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2,max = 50,message = "Имя автора не может быть не менее 2 символов и не более 50 символов")
    @Column(name = "author")
    private String author;
    @NotEmpty(message = "Поле описание не может быть пустым")
    @Size(min = 2,message = "Поле описание не может быть менее 2 символов")
    @Column(name = "annotation")
    private String annotation;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
