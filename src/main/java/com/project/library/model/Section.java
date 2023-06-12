package com.project.library.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Integer sectionId;
    @Column(name = "section_name")
    private String sectionName;
    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

//    @ManyToMany(mappedBy = "section", fetch = FetchType.LAZY)
//    private Set<Employee> employees = new HashSet<>();
//
//    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
//    private Set<Book> books = new HashSet<>();

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Section(){

    }

    public Section(Integer sectionId, String sectionName, String description, String location) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.description = description;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location  +
                '}';
    }


}
