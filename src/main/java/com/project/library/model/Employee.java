package com.project.library.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    private String fname;

    @Column(name = "last_name")
    private String lname;

    @ManyToMany
    @JoinTable(name = "employees_section",
            joinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "section_id", referencedColumnName = "section_id",
                            nullable = false, updatable = false)})
    private Set<Section> section = new HashSet<>();

    public Employee() {
    }

    public Employee(int employeeId, String fname, String lname) {
        this.employeeId = employeeId;
        this.fname = fname;
        this.lname = lname;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}
