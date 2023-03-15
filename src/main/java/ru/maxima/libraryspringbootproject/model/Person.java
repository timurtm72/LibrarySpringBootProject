package ru.maxima.libraryspringbootproject.model;

import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Book> personBooks;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2,max = 50,message = "Фамилия, имя и отчество должны быть не менее 5 символов и не более 50 символов")
    @Column(name = "name")
    private String name;
    @Min(value = 1930,message = "Год рождения должен быть больше 1930 года")
    @Column(name = "age")
    private Integer age;
    @NotEmpty(message = "Поле электронной почты не может быть пустым")
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Поле номера телефона не может быть пустым")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotEmpty(message = "Поле пароля не может быть пустым")
    @Size(min = 5,max = 50,message = "Пароль должен быть не менее 5 символов и не более 50 символов")
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
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

    public Person() {
    }

    public Person(Long id, List<Book> personBooks, String name, Integer age, String email, String phoneNumber, String password, String role, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime removedAt, String createdPerson, String updatedPerson, String removedPerson) {
        this.id = id;
        this.personBooks = personBooks;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.removedAt = removedAt;
        this.createdPerson = createdPerson;
        this.updatedPerson = updatedPerson;
        this.removedPerson = removedPerson;
    }

//    public void addBook(Book book){
//        if(personBooks != null) {
//            personBooks.add(book);
//        }
//    }
//    public void removeBook(Book book){
//        if(personBooks != null && !personBooks.isEmpty() ) {
//            personBooks.remove(book);
//        }
//    }

    public List<Book> getBooks() {
        return personBooks;
    }

    public void setBooks(List<Book> books) {
        this.personBooks = books;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
