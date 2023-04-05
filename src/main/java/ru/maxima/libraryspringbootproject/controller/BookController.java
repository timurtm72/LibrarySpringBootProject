package ru.maxima.libraryspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringbootproject.model.Book;
import ru.maxima.libraryspringbootproject.model.SearchBook;
import ru.maxima.libraryspringbootproject.service.BookService;
import ru.maxima.libraryspringbootproject.service.PeopleService;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showBooks(Model model){
        model.addAttribute("books",bookService.findBooks());
        return "books/index_book";
    }
    @GetMapping("/{id}")
    public String findBook(@PathVariable("id") Long id, Model model){
        Book book = bookService.findBook(id);
        model.addAttribute("book",book);
        model.addAttribute("person",book.getPerson());
        model.addAttribute("people",peopleService.findAll());
        model.addAttribute("count",bookService.findSimilarBooks(book));
        return "books/show_book";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book,@ModelAttribute("searchBook") SearchBook searchBook) {
        return "books/new_book";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("searchBook") SearchBook searchBook,@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new_book";
        }
        bookService.saveCreatedBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookService.findBook(id));
        return "books/edit_book";
    }

    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             @PathVariable("id") Long id,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit_book";
        }
        bookService.updateBook(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/free")
    public String freeBook(@RequestParam Long book_id, @RequestParam Long person_id, Model model) {
        bookService.freeBook(book_id);
        return "redirect:/books/" + book_id;
    }

    @PostMapping("/select")
    public String addBookInUser(@RequestParam Long id, @RequestParam Long book_id, Model model) {
        bookService.addBookInUser(id, book_id);
        return  "redirect:/books/" + book_id;
    }
    @GetMapping("/search")
    public String searchBook(@ModelAttribute("book") Book book,@ModelAttribute("searchBook") @Valid SearchBook searchBook,
                             Model model,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new_book";
        }
        Book result = bookService.searchBook(searchBook);
        model.addAttribute("book",result);
        model.addAttribute("searchBook",result);
        return "books/new_book";
    }
}
