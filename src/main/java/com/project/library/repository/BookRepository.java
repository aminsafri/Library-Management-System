package com.project.library.repository;

import com.project.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM staff where book_id = :id", nativeQuery = true)
    Book findBookById(@Param("id") int id);
}
