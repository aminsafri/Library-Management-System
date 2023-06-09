package com.project.library.repository;

import com.project.library.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
    @Query(value = "SELECT * FROM borrower where borrower_id = :id", nativeQuery = true)
    Borrower findBorrowerById(@Param("id") int id);
}
