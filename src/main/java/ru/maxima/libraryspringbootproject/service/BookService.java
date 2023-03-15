package ru.maxima.libraryspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringbootproject.model.Book;
import ru.maxima.libraryspringbootproject.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;
    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Transactional
    public List<Book> findAll(){
        return booksRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }
    @Transactional
    public Book findOneBook(Long id){
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findUserBooks(Long id) {
        return booksRepository.findBooksByPersonIdOrderByIdAsc(id);
    }
}
