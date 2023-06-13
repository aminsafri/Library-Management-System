package com.project.library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Copy> copies;

    public Book() {

    }

    public Book(Integer bookId, String bookName, String author, Section section, List<Copy> copies) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.section = section;
        this.copies = copies;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Copy> getCopy() {
        return copies;
    }

    public void setCopy(List<Copy> copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
