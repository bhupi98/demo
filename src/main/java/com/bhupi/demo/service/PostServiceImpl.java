package com.bhupi.demo.service;

import com.bhupi.demo.model.Post;
import com.bhupi.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl {

    @Autowired
    PostRepo postRepo;
    public List<Post> getPosts(){
        return postRepo.findAll();
    }

    public Post addPost(Post post){
        return postRepo.save(post);
    }
}
