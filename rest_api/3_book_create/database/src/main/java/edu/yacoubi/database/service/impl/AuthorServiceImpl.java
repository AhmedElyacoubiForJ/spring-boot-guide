package edu.yacoubi.database.service.impl;

import edu.yacoubi.database.model.entity.Author;
import edu.yacoubi.database.repository.AuthorRepository;
import edu.yacoubi.database.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
