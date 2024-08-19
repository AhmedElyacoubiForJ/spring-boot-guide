package edu.yacoubi.database.services.impl;

import edu.yacoubi.database.model.entities.AuthorEntity;
import edu.yacoubi.database.repositories.AuthorRepository;
import edu.yacoubi.database.services.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
