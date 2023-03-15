package ru.maxima.springboottest.ProjectSpringBoot1.repositories;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.maxima.springboottest.ProjectSpringBoot1.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
    Optional<Person> findFirstByNameAndAge(String name,int age);
    List<Person> findByPasswordNotContainingOrderByIdAsc(String password);
}
