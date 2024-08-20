package edu.yacoubi.database.service;

import edu.yacoubi.database.model.entity.Book;

public interface IBookService {
    public Book createBook(String isbn, Book book);
}
