package ru.maxima.libraryspringbootproject.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    @Transactional
    public List<Person> findAll(){
        return peopleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    @Transactional
    public Person findOnePerson(Long id){
        return peopleRepository.findById(id).orElse(null);
    }
}
