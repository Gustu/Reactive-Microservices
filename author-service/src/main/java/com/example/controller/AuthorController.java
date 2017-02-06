package com.example.controller;

import com.example.controller.model.AuthorCreateViewModel;
import com.example.domain.Author;
import com.example.service.AuthorService;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public Observable<Author> getBooks() {
        return authorService.getAuthors();
    }


    @RequestMapping(method = RequestMethod.POST)
    public Observable<Author> createBook(@RequestBody @Valid AuthorCreateViewModel authorCreateViewModel) {
        return authorService.createAuthor(Author.builder()
            .name(authorCreateViewModel.getName())
            .nationality(authorCreateViewModel.getNationality())
            .build());
    }

}
