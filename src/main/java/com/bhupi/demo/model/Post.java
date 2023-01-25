package com.bhupi.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title ;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_post_id")
    List<Comment> comments=new ArrayList<>();

    //http://localhost:9090/swagger-ui/
}
