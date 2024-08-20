package edu.yacoubi.database.service.impl;

import edu.yacoubi.database.model.entity.Author;
import edu.yacoubi.database.repository.AuthorRepository;
import edu.yacoubi.database.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }
}
