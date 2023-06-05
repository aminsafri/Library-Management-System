package com.project.library.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrower_id")
    private Integer borrowerId;
    @Column(name = "first_name")
    private String fname;
    @Column(name = "last_name")
    private String lname;
    @Column(name = "email")
    private Integer email;
    @Column(name = "phone_number")
    private Integer phonenumber;



    public Borrower() {
    }

    public Borrower(Integer borrowerId, String fname, String lname, Integer email, Integer phonenumber) {
        this.borrowerId = borrowerId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phonenumber = phonenumber;

    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerId=" + borrowerId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email=" + email +
                ", phonenumber=" + phonenumber +
                '}';
    }
}