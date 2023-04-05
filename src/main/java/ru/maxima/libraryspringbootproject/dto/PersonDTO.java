package ru.maxima.libraryspringbootproject.dto;

import jakarta.persistence.*;


import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import ru.maxima.libraryspringbootproject.model.Book;

import java.util.List;

@Component
public class PersonDTO {
    private Long id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 4,max = 50,message = "Фамилия, имя и отчество должны быть не менее 5 символов и не более 50 символов")
    private String name;

    @Min(value = 5,message = "Возраст не может быть меньше 5 лет")
    private Integer age;
    @NotEmpty(message = "Поле электронной почты не может быть пустым")
    private String email;
    @NotEmpty(message = "Поле номера телефона не может быть пустым")
    private String phoneNumber;

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

}
