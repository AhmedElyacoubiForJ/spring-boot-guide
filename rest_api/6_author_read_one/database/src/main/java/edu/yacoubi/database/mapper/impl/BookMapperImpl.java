package edu.yacoubi.database.mapper.impl;

import edu.yacoubi.database.mapper.Mapper;
import edu.yacoubi.database.model.dto.BookDto;
import edu.yacoubi.database.model.entity.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements Mapper<Book, BookDto> {
    private final ModelMapper modelMapper;

    @Override
    public BookDto mapTo(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public Book mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
