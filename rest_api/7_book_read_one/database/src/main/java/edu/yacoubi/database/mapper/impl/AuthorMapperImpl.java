package edu.yacoubi.database.mapper.impl;

import edu.yacoubi.database.mapper.Mapper;
import edu.yacoubi.database.model.dto.AuthorDto;
import edu.yacoubi.database.model.entity.Author;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorMapperImpl implements Mapper<Author, AuthorDto> {
    private final ModelMapper modelMapper;

    @Override
    public AuthorDto mapTo(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
