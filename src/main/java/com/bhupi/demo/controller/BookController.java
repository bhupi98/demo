package com.bhupi.demo.controller;

import com.bhupi.demo.model.Author;
import com.bhupi.demo.model.Book;
import com.bhupi.demo.service.AuthorServiceImpl;
import com.bhupi.demo.service.BookServiceImpl;
import com.bhupi.demo.util.CustomException;
import com.bhupi.demo.util.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    GlobalException ge=new GlobalException();
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    AuthorServiceImpl authorService;
    @GetMapping("/books")
    ResponseEntity<List<?>> books(){
        try{
            List<Book>list=bookService.getBooks();
            if(list.isEmpty()){
                throw new CustomException("there is no data");
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookslist")
    ResponseEntity<Page<?>> books(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize){
        try{
            Page<Book> list=bookService.findListByPage(pageNumber,pageSize);
            System.out.println("listof books"+list);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addBook")
    ResponseEntity<Book>saveBook(@RequestBody Book book){
        try{
            Book saveBook=bookService.addBook(book);
            System.out.println("Book is being saved"+saveBook);
            return new ResponseEntity<Book>(saveBook,HttpStatus.OK);
        }catch (HttpServerErrorException.BadGateway e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/book/{bookid}")
    ResponseEntity<Book>getBookById(@PathVariable int bookid){
        try{
            System.out.println("bookid"+bookid);
           Optional<Book> book= bookService.getBook(bookid);
            System.out.println("book status"+book);
           if(book.isEmpty()){
               Integer[]arr=new Integer[]{};
               return new ResponseEntity(arr,HttpStatus.OK);
           }else{
               return new ResponseEntity(book,HttpStatus.OK);
           }

        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping ("/book/{bookid}")
    ResponseEntity<String>deleteBook(@PathVariable int bookid){
        try{
            System.out.println("bookid"+bookid);
            bookService.deleteBook(bookid);
            return new ResponseEntity("book deleteted successfully",HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/book")
    ResponseEntity<Book>getBookByAuthor(@RequestParam String author){
        try{
            System.out.println("bookauthor"+author);
            Optional<Book> book= bookService.getBookByAuthor(author);
            System.out.println("book status"+book);
            if(book.isEmpty()){
                Integer[]arr=new Integer[]{};
                return new ResponseEntity(arr,HttpStatus.OK);
            }else{
                return new ResponseEntity(book,HttpStatus.OK);
            }

        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/bookby")
    ResponseEntity<Book>getBookByBookAndAuthor(@RequestParam String bookname,@RequestParam String authorname){
        try{
            System.out.println("bookauthor"+authorname);
            Optional<Book> book= bookService.getBookByBookAndAuthor(bookname,authorname);
            System.out.println("book status"+book);
            if(book.isEmpty()){
                Integer[]arr=new Integer[]{};
                return new ResponseEntity(arr,HttpStatus.OK);
            }else{
                return new ResponseEntity(book,HttpStatus.OK);
            }

        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
