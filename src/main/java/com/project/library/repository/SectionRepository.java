package com.project.library.repository;

import com.project.library.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    @Query(value = "SELECT * FROM section where section_id = :id",nativeQuery = true)
    Section findSectionById(@Param("id") int id);
}
