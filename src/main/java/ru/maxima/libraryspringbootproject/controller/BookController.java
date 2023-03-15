package ru.maxima.libraryspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringbootproject.model.Book;
import ru.maxima.libraryspringbootproject.service.BookService;
import ru.maxima.libraryspringbootproject.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private PeopleService peopleService;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String findAllBooks(Model model){
        model.addAttribute("books",bookService.findAll());
        return "books/index_book";
    }
    @GetMapping("/{id}")
    public String findOneBook(@PathVariable("id") Long id,Model model){
        model.addAttribute("book",bookService.findOneBook(id));
        model.addAttribute("person",peopleService.findOnePerson(id));
        model.addAttribute("people",peopleService.findAll());
        return "books/show_book";
    }
}
