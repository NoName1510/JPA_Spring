package com.demo.jpa;

import com.demo.jpa.config.JPAConfig;
import com.demo.jpa.config.SpringConfig;
import com.demo.jpa.entity.BookEntity;
import com.demo.jpa.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");

    public static void main(String[] args) {
//         createNewBook();
//        readBook(9);
//        updateBook();
//        delBook();
//        findByAuthor("Roger");
//        getBookStartWith("Java");
        getBookWherePriceLessThanAndNumOfPageGreaterThan(200.0, 200);
    }


    private static void createNewBook() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setCategory("IT Book");
        bookEntity.setIsbn("ISxxxxxxx");
        bookEntity.setNumberOfPage(234);
        bookEntity.setPrice(20.5);
        bookEntity.setPulishDate(LocalDate.now());

        BookEntity result = bookRepository.save(bookEntity);

        if (result != null) {
            System.out.println("Da luu thanh cong, book id =" + bookEntity.getId());
        }
    }

    private static void readBook() {

        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        System.out.println("Found " + bookList.size() + " book(s) in the table book");
        System.out.println("they are: ");
        for (BookEntity book : bookList) {
            System.out.println(book.toString());
        }

    }

    private static void readBook(int bookID) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookID);
        if (bookEntity.isPresent()) {
            System.out.println(bookID);
            System.out.println(bookEntity.toString());
        } else {
            System.out.println("no find book");
        }
    }

    private static void updateBook() {
        Optional<BookEntity> bookEntity = bookRepository.findById(1);
        if (bookEntity.isPresent()) {
            System.out.println("book data befor update");
            System.out.println(bookEntity.toString());

            BookEntity book = bookEntity.get();
            book.setAuthor("tsvd");
            book.setNumberOfPage(199);
            book.setPrice(201);
            bookRepository.save(book);

            System.out.println("book data after update");
            System.out.println(bookEntity.toString());
        }
    }

    private static void delBook() {
//        bookRepository.deleteById(1);

        Optional<BookEntity> bookEntity = bookRepository.findById(4);
        if (bookEntity.isPresent()) {
            bookRepository.delete(bookEntity.get());
        }

//        bookRepository.deleteAll();
    }

    private static void findByAuthor(String author) {
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findByAuthor(author);
        if (bookList.size() != 0) {
            System.out.println("they are");
            for (BookEntity book : bookList) {
                System.out.println(book.toString());
            }
        }
    }

    private static void getBookStartWith(String name) {
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.getBookStartWith(name);
        if (bookList.size() != 0) {
            System.out.println("they are");
            for (BookEntity book : bookList) {
                System.out.println(book.toString());
            }
        }
    }


    private static void getBookWherePriceLessThanAndNumOfPageGreaterThan(double price, int numberPage) {
        List<BookEntity> bookList = bookRepository.getBookWherePriceLessThanAndNumOfPageGreaterThan(price, numberPage);
        if (bookList.size() != 0) {
            System.out.println("they are");
            for (BookEntity book : bookList) {
                System.out.println(book.toString());
            }
        }
    }
}