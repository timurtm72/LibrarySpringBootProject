package ru.maxima.libraryspringbootproject.model;

import jakarta.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="person_id")
    private Person person;
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
    @Size(min = 2,message = "Поле описание не может быть не менее 2 символов")
    @Column(name = "annotation")
    private String annotation;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "removed_at")
    private LocalDateTime removedAt;
    @Column(name = "created_person")
    private String createdPerson;
    @Column(name = "updated_person")
    private String updatedPerson;
    @Column(name = "removed_person")
    private String removedPerson;

    public Book() {
    }

    public Book(Long id, Person person, String name, Integer yearOfProduction, String author, String annotation, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime removedAt, String createdPerson, String updatedPerson, String removedPerson) {
        this.id = id;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
