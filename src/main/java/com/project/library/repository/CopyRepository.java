package com.project.library.repository;

import com.project.library.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CopyRepository extends JpaRepository<Copy, Integer> {
    @Query(value = "SELECT * FROM copy where copy_id = :id", nativeQuery = true)
    Copy findCopyById(@Param("id") int id);
}
