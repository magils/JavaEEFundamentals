package com.mgil.labs.java.ee.injection;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class BookService {



    @PostConstruct
    public void init(){
        System.out.println("Hello World!");
        Book b = createBook("Dummy");
        System.out.println(b);
    }

    //@Inject
    private NumberGenerator generator;

    public Book createBook(String title){
        return new Book(title,generator.generateNumber());
    }

}
