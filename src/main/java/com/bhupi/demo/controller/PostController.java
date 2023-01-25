package com.bhupi.demo.controller;

import com.bhupi.demo.model.Book;
import com.bhupi.demo.model.Post;
import com.bhupi.demo.service.PostServiceImpl;
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
public class PostController {

    @Autowired
    PostServiceImpl postService;
    @GetMapping("/posts")
    ResponseEntity<List<?>> books(){
        try{
            List<Post>list=postService.getPosts();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addPost")
    ResponseEntity<Post>saveBook(@RequestBody Post post){
        try{
            Post savePost=postService.addPost(post);
            System.out.println("Book is being saved"+savePost);
            return new ResponseEntity<Post>(savePost,HttpStatus.OK);
        }catch (HttpServerErrorException.BadGateway e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
