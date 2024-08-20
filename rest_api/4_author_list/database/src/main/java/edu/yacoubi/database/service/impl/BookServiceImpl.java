package edu.yacoubi.database.service.impl;

import edu.yacoubi.database.model.entity.Book;
import edu.yacoubi.database.repository.BookRepository;
import edu.yacoubi.database.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;

    @Override
    public Book createBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
