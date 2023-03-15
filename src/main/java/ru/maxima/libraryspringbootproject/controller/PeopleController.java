package ru.maxima.libraryspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.service.BookService;
import ru.maxima.libraryspringbootproject.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PeopleService peopleService;
    private BookService bookService;
    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping
    public String findAllPersons(Model model){
        List<Person> people = peopleService.findAll();
        model.addAttribute("people",people);
        return "people/index_person";
    }
    @GetMapping("/{id}")
    public String findOnePerson(@PathVariable("id") Long id,Model model){
        model.addAttribute("person",peopleService.findOnePerson(id));
        model.addAttribute("user_books",bookService.findUserBooks(id));

        return "people/show_person";
    }
}
