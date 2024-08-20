package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Book;

public interface IBookService {
    Book createBook(String isbn, Book book);
}
