package com.example.controller;

import com.example.controller.model.AuthorBooksViewModel;
import com.example.controller.model.BookViewModel;
import com.example.domain.Book;
import com.example.service.BookService;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BookController {

    private final BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public Observable<Book> getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping(value = "/grouped", method = RequestMethod.GET)
    public Observable<AuthorBooksViewModel> getBooksGroupedByAuthor() {
        return bookService.getBooks()
            .groupBy(Book::getAuthor)
            .flatMap(group -> group.map(Book::getTitle).collect(AuthorBooksViewModel::new, (authorBooks, title) -> {
                authorBooks.getTitles().add(title);
            }).toObservable());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Observable<Book> createBook(@RequestBody @Valid BookViewModel bookViewModel) {
        return bookService.createBook(Book.builder()
            .title(bookViewModel.getTitle())
            .author(bookViewModel.getAuthor())
            .build());
    }

}
