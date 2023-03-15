package ru.maxima.libraryspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.service.BookService;
import ru.maxima.libraryspringbootproject.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private BookService bookService;
    @Autowired


    @GetMapping
    public String findAllPersons(Model model){
        List<Person> people = peopleService.findAll();
        model.addAttribute("people",people);
        return "people/index_person";
    }

    public String findOnePerson(@PathVariable("id") Long id,Model model){
        model.addAttribute("person",peopleService.FindOnePerson(id));
        return "people/Show_person";
    }
}
