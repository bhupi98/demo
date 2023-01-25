package com.bhupi.demo.controller;

import com.bhupi.demo.model.Author;
import com.bhupi.demo.model.Book;
import com.bhupi.demo.service.AuthorServiceImpl;
import com.bhupi.demo.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;

    @GetMapping("/authors")
    ResponseEntity<List<?>> books(){
        try{
            List<Author>list=authorService.getAuthors();
            if(list.size()!=0){
                throw new CustomException("there is no data");
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addAuthor")
    ResponseEntity<Author> saveBook(@RequestBody Author author){
        try{
            Author saveAuthor=authorService.addAuthor(author);
            System.out.println("Book is being saved"+saveAuthor);
            return new ResponseEntity<Author>(saveAuthor, HttpStatus.OK);
        }catch (HttpServerErrorException.BadGateway e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

}
