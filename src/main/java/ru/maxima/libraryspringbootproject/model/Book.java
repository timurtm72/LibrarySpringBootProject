package ru.maxima.libraryspringbootproject.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

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
    @Size(min = 2,message = "Поле описание не может быть менее 2 символов")
    @Column(name = "annotation")
    private String annotation;
    @Column(name = "removed")
    private boolean removed;
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

    public Book(Long id, Person person, String name, Integer yearOfProduction, String author, String annotation, boolean removed, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime removedAt, String createdPerson, String updatedPerson, String removedPerson) {
        this.id = id;
        this.person = person;
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.author = author;
        this.annotation = annotation;
        this.removed = removed;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", person=" + person +
                ", name='" + name + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", author='" + author + '\'' +
                ", annotation='" + annotation + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", removedAt=" + removedAt +
                ", createdPerson='" + createdPerson + '\'' +
                ", updatedPerson='" + updatedPerson + '\'' +
                ", removedPerson='" + removedPerson + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(name, book.name)) return false;
        if (!Objects.equals(yearOfProduction, book.yearOfProduction))
            return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (yearOfProduction != null ? yearOfProduction.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
