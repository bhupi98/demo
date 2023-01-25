package com.bhupi.demo.service;

import com.bhupi.demo.model.Book;
import com.bhupi.demo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl {


    @Autowired
    BookRepo bookRepo;

    public Page<Book> findListByPage(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
       // final Pageable contactsPageable = PageRequest.of(pageNumber, pageSize);
        Page<Book> books=bookRepo.findAll(pageable);
        List<Book> listOfPosts = books.getContent();
        System.out.println("books based on page"+books);
        System.out.println(listOfPosts);
        return books;
    }
    public List<Book> getBooks(){
       List<Book>books= bookRepo.findAll();
       return books;
    };

    public Book addBook(Book book){
        Book saveBook=bookRepo.save(book);
        return saveBook;
    }

    public Optional<Book> getBook(int bookid){
      Optional<Book> book= bookRepo.findById(bookid);
      return book;
    }
    public Optional<Book>getBookByAuthor(String author){
        System.out.println(author);
        Optional<Book>authorBook=bookRepo.findByAuthor(author);
        System.out.println(authorBook);
        return authorBook;
    }

    public void deleteBook(int bookid){
        bookRepo.deleteById(bookid);
    }
    public Optional<Book>getBookByBookAndAuthor(String bookname,String authorname){
        System.out.println(authorname);
        Optional<Book>authorBook=bookRepo.findByBookAndAuthor(bookname,authorname);
        System.out.println(authorBook);
        return authorBook;
    }

}
