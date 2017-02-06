package com.example.service;

import com.example.domain.Author;
import com.example.repository.AuthorRepository;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Observable<Author> getAuthors() {
        return Observable.fromIterable(authorRepository.findAll());
    }

    public Observable<Author> createAuthor(Author author) {
        return Observable.just(authorRepository.save(author));
    }

}
