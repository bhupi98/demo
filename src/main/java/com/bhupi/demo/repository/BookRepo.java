package com.bhupi.demo.repository;

import com.bhupi.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query( value = "SELECT * FROM book u WHERE u.authorname = ?1",
            nativeQuery = true)
    Optional<Book> findByAuthor(String author);
    @Query(value="SELECT * FROM book b  WHERE b.bookname=:bookname and b.authorname=:authorname",nativeQuery = true)
    Optional<Book> findByBookAndAuthor(@Param("bookname")String bookname, @Param("authorname") String authorname);
}
