package ru.maxima.libraryspringbootproject.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringbootproject.dto.PersonDTO;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.service.PeopleService;
import ru.maxima.libraryspringbootproject.service.RegistrationService;
import ru.maxima.libraryspringbootproject.exception_person.*;
import ru.maxima.libraryspringbootproject.util.Response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/people")
public class PeopleRestController {
    private final PeopleService peopleService;
    private final PersonDTO personDTO;
    private final ModelMapper modelMapper;
    private final RegistrationService registrationService;
    @Autowired
    public PeopleRestController(PeopleService peopleService, PersonDTO personDTO, ModelMapper modelMapper, RegistrationService registrationService) {
        this.peopleService = peopleService;
        this.personDTO = personDTO;
        this.modelMapper = modelMapper;
        this.registrationService = registrationService;
    }

    @GetMapping()
    public List<PersonDTO> findPeople() {
        List<PersonDTO> people = peopleService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
        if(people == null || people.size() == 0){
            throw new PeopleNotFoundException();
        }
        return people;
    }

    @GetMapping("/{id}")
    public PersonDTO findPerson(@PathVariable("id") Long id) {
        PersonDTO personDTO = null;
        Person person = peopleService.findPerson(id);
        if(person != null) {
            personDTO = convertToPersonDTO(person);
        }else{
            throw new PersonNotFoundException();
        }
        return personDTO;
    }


//    @PostMapping()
//    public ResponseEntity<PersonDTO> registerPerson(@RequestBody @Valid Person person,
//                                                 BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder bld = new StringBuilder();
//            bindingResult.getFieldErrors().forEach(error -> {
//                bld.append(error.getField())
//                        .append(" - ")
//                        .append(error.getDefaultMessage())
//                        .append(";");
//            });
//            throw new PersonNotRegisteredException(bld.toString());
//        }
//        Person newPerson = registrationService.saveRegisteredPerson(person);
//        return ResponseEntity.accepted().body(convertToPersonDTO(newPerson));
//    }

    @PostMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson( @RequestBody @Valid Person updatedPerson,
                                                   @PathVariable Long id,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder bld = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                bld.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            });
            throw new PersonNotUpdatedException(bld.toString());
        }
        Person person = peopleService.findPerson(id);
        if(person  == null){
            throw new PersonNotFoundException();
        }
        Person newPerson = peopleService.updatePerson(id,updatedPerson);
        return ResponseEntity.accepted().body(convertToPersonDTO(newPerson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePerson(@PathVariable("id") Long id){
        Person person = peopleService.findPerson(id);
        if(person == null){
            throw new PersonNotDeletedException();
        }
        peopleService.delete(id);
        return ResponseEntity.accepted().body(new Response("Удаление прошло успешно", new Date()));
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Пользователь не найден",
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PeopleNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Список пользователей пуст",
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PersonNotRegisteredException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PersonNotUpdatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PersonNotDeletedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Удаляемого пользователя нет в списке",
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
