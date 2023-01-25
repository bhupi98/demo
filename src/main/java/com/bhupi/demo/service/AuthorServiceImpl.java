package com.bhupi.demo.service;

import com.bhupi.demo.model.Author;
import com.bhupi.demo.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl {
    @Autowired
    AuthorRepo authorRepo;

    public Author addAuthor(Author author){
        return authorRepo.save(author);
    }
    public List<Author> getAuthors(){return authorRepo.findAll();};
}
