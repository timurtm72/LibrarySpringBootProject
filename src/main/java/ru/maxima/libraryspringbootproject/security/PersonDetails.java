package ru.maxima.libraryspringbootproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.maxima.libraryspringbootproject.model.Person;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private Person person;

    @Autowired
    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }
    @Override
    public String getPassword() {
        return this.person.getPassword();
    }
    @Override
    public String getUsername() {
        return this.person.getName();
    }

    @Override//Срок действия учетной записи не истек
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override//Учетная запись не заблокирована
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override//Срок действия учетных данных не истек
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    // Нужно, чтобы получать данные аутентифицированного пользователя
    public Person getPerson(){return this.person;};
}
