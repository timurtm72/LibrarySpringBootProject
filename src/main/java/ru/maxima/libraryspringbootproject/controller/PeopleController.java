package ru.maxima.libraryspringbootproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.service.BookService;
import ru.maxima.libraryspringbootproject.service.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;
    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String findPeople(Model model){
        model.addAttribute("people",peopleService.findAll());
        model.addAttribute("role",peopleService.findRoleByName());
        return "people/index_person";
    }
    @GetMapping("/{id}")
    public String findPerson(@PathVariable("id") Long id, Model model){
        model.addAttribute("person",peopleService.findPerson(id));
        model.addAttribute("user_books",bookService.findUserBooks(id));

        return "people/show_person";
    }

//    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("person")Person person){
//        return "people/new_person";
//    }
//
//    @PostMapping()
//    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
//        if(bindingResult.hasErrors()) {
//            return "people/new_person";
//        }
//
//        peopleService.saveCreatedPerson(person);
//        return "redirect:/people";
//    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") Long id){
        model.addAttribute("person",peopleService.findPerson(id));
        return "people/edit_person";
    }

    @PostMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,@PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "people/edit_person";
        }
        peopleService.updatePerson(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        peopleService.delete(id);
        return "redirect:/people";
    }

}
