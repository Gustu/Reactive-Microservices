package com.example.service;

import com.example.domain.Book;
import com.example.repository.BookRepository;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BookService {

    private final BookRepository bookRepository;

    public Observable<Book> getBooks() {
        return Observable.fromIterable(bookRepository.findAll());
    }

    public Observable<Book> createBook(Book book) {
        return Observable.just(bookRepository.save(book));
    }

}
