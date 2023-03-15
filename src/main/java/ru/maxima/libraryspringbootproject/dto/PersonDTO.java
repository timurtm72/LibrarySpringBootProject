package ru.maxima.libraryspringbootproject.dto;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class PersonDTO {
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
}
