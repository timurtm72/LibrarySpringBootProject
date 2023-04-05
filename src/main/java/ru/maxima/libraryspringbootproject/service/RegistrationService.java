package ru.maxima.libraryspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.repositories.PeopleRepository;

import java.time.LocalDateTime;

@Service
public class RegistrationService {
    private final PeopleService peopleService;
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleService peopleService, PeopleRepository peopleRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.peopleService = peopleService;
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public Person saveRegisteredPerson(Person registeredPerson) {
        Person person = new Person();
        if (registeredPerson != null) {
            person.setName(registeredPerson.getName());
            person.setAge(registeredPerson.getAge());
            person.setEmail(registeredPerson.getEmail());
            person.setPhoneNumber(registeredPerson.getPhoneNumber());
            person.setCreatedPerson(registeredPerson.getName());
            person.setCreatedAt(LocalDateTime.now());
            person.setRemoved(false);
            person.setRole("USER");
            String password = registeredPerson.getPassword();
            person.setPassword(passwordEncoder.encode(password));
            peopleRepository.save(person);
            return person;
        } else {
            return null;
        }
    }
}
