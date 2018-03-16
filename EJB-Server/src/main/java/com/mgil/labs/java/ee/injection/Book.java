package com.mgil.labs.java.ee.injection;

public class Book {

    private String title;
    private String bookNumber;

    public Book() {
    }

    public Book(String title, String bookNumber) {
        this.title = title;
        this.bookNumber = bookNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }
}
