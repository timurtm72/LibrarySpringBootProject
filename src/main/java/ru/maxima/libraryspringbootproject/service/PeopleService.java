package ru.maxima.libraryspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.repositories.PeopleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public PeopleService( PeopleRepository peopleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<Person> findAll() {
        return peopleRepository.findAllByRemovedIsFalseOrderByIdAsc();
    }

    @Transactional
    public Person findPerson(Long id) {
        Person person = peopleRepository.findPersonById(id).orElse(null);
        if (person != null && person.isRemoved() == false) {
            return person;
        }
        return null;
    }

    @Transactional
    public Person findByName(String name) {
        return peopleRepository.findByName(name).orElse(null);
    }

//    @Transactional
//    public void saveCreatedPerson(Person person) {
//        if (person != null) {
//            person.setCreatedPerson(getUserName());
//            person.setCreatedAt(LocalDateTime.now());
//            person.setRole("USER");
//            peopleRepository.save(person);
//        } else {
//            throw new NullPointerException("Null pointer exception");
//        }
//    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userName;
    }

    @Transactional
    public Person updatePerson(Long id, Person updatedPerson) {
        if (updatedPerson != null) {
            Person person = findPerson(id);
            if(person == null){
                return null;
            }
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            person.setEmail(updatedPerson.getEmail());
            person.setPhoneNumber(updatedPerson.getPhoneNumber());
            person.setUpdatedAt(LocalDateTime.now());
            person.setUpdatedPerson(getUserName());
            String password = updatedPerson.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            person.setPassword(encodedPassword);
            peopleRepository.save(person);
            return person;
        } else {
            return null;
        }
    }
    @Transactional
    public String findRoleByName(){
        String userName = getUserName();
        Person person = peopleRepository.findByName(userName ).orElse(null);
        if(person == null) return null;
        return person.getRole();
    }

    @Transactional
    public Person delete(Long id) {
        Person person = findPerson(id);
        if (person != null) {
            person.setRemovedPerson(getUserName());
            person.setRemovedAt(LocalDateTime.now());
            person.setRemoved(true);
            peopleRepository.save(person);
            return person;
        } else {
            return null;
        }
    }

    @Transactional
    public List<Person> findAllWithMatchingPassword() {
        List<Person> people = peopleRepository.findAllByRemovedIsFalseAndPasswordIsNotNullOrderByIdAsc();
        return people;
    }

    @Transactional
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }
}

