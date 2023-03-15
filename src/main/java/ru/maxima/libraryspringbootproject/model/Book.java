package ru.maxima.libraryspringbootproject.model;

import java.time.LocalDateTime;

public class Book {
    private Long id;
    private String name;
    private Integer yearOfProduction;
    private String author;
    private String annotation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime removedAt;
    private String createdPerson;
    private String updatedPerson;
    private String removedPerson;

    public Book() {
    }

    public Book(Long id, String name, Integer yearOfProduction, String author, String annotation, LocalDateTime createdAt,
                LocalDateTime updatedAt, LocalDateTime removedAt, String createdPerson, String updatedPerson, String removedPerson) {
        this.id = id;
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.author = author;
        this.annotation = annotation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.removedAt = removedAt;
        this.createdPerson = createdPerson;
        this.updatedPerson = updatedPerson;
        this.removedPerson = removedPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }

    public String getCreatedPerson() {
        return createdPerson;
    }

    public void setCreatedPerson(String createdPerson) {
        this.createdPerson = createdPerson;
    }

    public String getUpdatedPerson() {
        return updatedPerson;
    }

    public void setUpdatedPerson(String updatedPerson) {
        this.updatedPerson = updatedPerson;
    }

    public String getRemovedPerson() {
        return removedPerson;
    }

    public void setRemovedPerson(String removedPerson) {
        this.removedPerson = removedPerson;
    }
}
