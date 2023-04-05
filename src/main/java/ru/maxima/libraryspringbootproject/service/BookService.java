package ru.maxima.libraryspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringbootproject.model.Book;
import ru.maxima.libraryspringbootproject.model.Person;
import ru.maxima.libraryspringbootproject.model.SearchBook;
import ru.maxima.libraryspringbootproject.repositories.BooksRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;
    private final BooksRepository peopleRepository;
    private final PeopleService peopleService;

    @Autowired
    public BookService(BooksRepository booksRepository, BooksRepository peopleRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
        this.peopleService = peopleService;
    }

    @Transactional
    public List<Book> findBooks() {
        return booksRepository.findAllByRemovedIsFalseOrderByIdAsc();
    }

    @Transactional
    public Book findBook(Long id) {
        Book book = booksRepository.findBookById(id).orElse(null);
        if (book != null  && book.isRemoved() == false ) {
            return book;
        }
        return null;
    }

    @Transactional
    public List<Book> findUserBooks(Long id) {
        return booksRepository.findAllByPersonIdOrderByIdAsc(id);
    }

    @Transactional
    public Book saveCreatedBook(Book createdBook) {
        Book book = new Book();
        if (createdBook != null) {
            book.setName(createdBook.getName());
            book.setAuthor(createdBook.getAuthor());
            book.setAnnotation(createdBook.getAnnotation());
            book.setYearOfProduction(createdBook.getYearOfProduction());
            book.setCreatedPerson(getUserName());
            book.setCreatedAt(LocalDateTime.now());
            book.setRemoved(false);
            booksRepository.save(book);
            return book;
        } else {
            return null;
        }
    }

    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        if (updatedBook != null) {
            Book book = findBook(id);
            if(book == null){
                return null;
            }
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setAnnotation(updatedBook.getAnnotation());
            book.setYearOfProduction(updatedBook.getYearOfProduction());
            book.setUpdatedAt(LocalDateTime.now());
            book.setUpdatedPerson(getUserName());
            booksRepository.save(book);
            return book;
        } else {
            return null;
        }
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userName;
    }

    @Transactional
    public Book delete(Long id) {
        Book book = findBook(id);
        if (book != null) {
            book.setRemovedPerson(getUserName());
            book.setRemovedAt(LocalDateTime.now());
            book.setRemoved(true);
            book.setPerson(null);
            booksRepository.save(book);
            return book;
        } else {
           return null;
        }
    }

    @Transactional
    public Book freeBook(Long bookId) {
        Book book = findBook(bookId);
        if (book != null) {
            book.setPerson(null);
            booksRepository.save(book);
            return book;
        }else{
            return null;
        }
    }

    @Transactional
    public Book addBookInUser(Long id, Long bookId) {
        Book book = findBook(bookId);
        Person person = peopleService.findPerson(id);
        if (book != null && person != null) {
            book.setPerson(person);
            booksRepository.save(book);
            return book;
        } else {
            return null;
        }
    }

    public Integer findSimilarBooks(Book book) {
        List<Book> result = findBooks().stream().filter(item -> Objects.equals(item, book)).collect(Collectors.toList());
        return result.size();
    }

    public Book searchBook(SearchBook searchBook) {
        Book book = null;
        if (searchBook != null) {
            String name = searchBook.getName();
            book = booksRepository.findFirstByName(name).orElse(null);
            if (book == null) {
                return bookNotFound();
            } else {
                if (searchBook.getName() != null && (!searchBook.getName().isEmpty())) {
                    if (searchBook.getName().equals(book.getName())) {
                        return book;
                    }
                }
            }
        }
        return bookNotFound();
    }

    public Book bookNotFound() {
        Book book = new Book();
        book.setName("Книга с таким именем не найдена");
        return book;
    }
}