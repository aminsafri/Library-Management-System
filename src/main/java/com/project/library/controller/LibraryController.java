package com.project.library.controller;

import com.project.library.model.*;
import com.project.library.repository.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class LibraryController {

    private final BookRepository bookRepository;

    private final SectionRepository sectionRepository;

    private final BorrowerRepository borrowerRepository;

    private final CopyRepository copyRepository;

    private final EmployeeRepository employeeRepository;

    public LibraryController(BookRepository bookRepository, SectionRepository sectionRepository, BorrowerRepository borrowerRepository, CopyRepository copyRepository, EmployeeRepository employeeRepository) {

        this.bookRepository = bookRepository;
        this.sectionRepository = sectionRepository;
        this.borrowerRepository = borrowerRepository;
        this.copyRepository = copyRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "list-book";
    }

    @GetMapping("signup")
    public String showSignUpForm(Book book, Model model){
        model.addAttribute("sections", sectionRepository.findAll());
        return "add-book";
    }

    @PostMapping("add")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookRepository.save(book);
        return "redirect:list";
    }

    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "choose-book-to-update";
    }


    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("sections", sectionRepository.findAll());
        return "update-book";
    }


    @PostMapping("update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setBookId((int) id);
            return "index";
        }

        model.addAttribute("books", bookRepository.findAll());
        bookRepository.save(book);
        return "list-book";
    }

    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "choose-book-to-delete";
    }


    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        List<Copy> copy = book.getCopy(); // Get the books associated with the borrower

        // Remove the borrower from the books
        for (Copy copies : copy) {
            copies.setBorrower(null);
            copyRepository.save(copies);
        }

        bookRepository.delete(book);
        model.addAttribute("books", bookRepository.findAll());

        return "list-book";
    }

    //borrower

    @GetMapping("listborrower")
    public String showBorrowerList(Model model) {
        model.addAttribute("borrowers", borrowerRepository.findAll());
        return "list-borrower";
    }
    @GetMapping("signupborrower")
    public String showSignUpFormBorrower(Model model) {
        model.addAttribute("borrower", new Borrower());
        return "add-borrower";
    }


    @PostMapping("addborrower")
    public String addBorrower(@Valid Borrower borrower, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("borrower", borrower);
            return "add-borrower";
        }

        borrowerRepository.save(borrower);
        return "redirect:listborrower";
    }
    @GetMapping("updateborrower")
    public String showUpdateMainFormBorrower(Model model) {
        model.addAttribute("borrowers", borrowerRepository.findAll());
        return "choose-borrower-to-update";
    }


    @GetMapping("editborrower/{id}")
    public String showUpdateFormBorrower(@PathVariable("id") long id, Model model) {
        Borrower borrower = borrowerRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid borrower Id:" + id));
        model.addAttribute("borrower", borrower);
        return "update-borrower";
    }


    @PostMapping("updateborrower/{id}")
    public String updateBorrower(@PathVariable("id") long id, @Valid Borrower borrower, BindingResult result, Model model) {
        if (result.hasErrors()) {
            borrower.setBorrowerId((int) id);
            return "update-borrower";
        }

        model.addAttribute("borrowers", borrowerRepository.findAll());
        borrowerRepository.save(borrower);
        return "list-borrower";
    }

    @GetMapping("deleteborrower")
    public String showDeleteMainFormBorrower(Model model) {
        model.addAttribute("borrowers", borrowerRepository.findAll());
        return "choose-borrower-to-delete";
    }


    @GetMapping("deleteborrower/{id}")
    public String deleteBorrower(@PathVariable("id") long id, Model model) {
        Borrower borrower = borrowerRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid borrower Id:" + id));

        List<Copy> copy = borrower.getCopy();

        for (Copy copies : copy) {
            copies.setBorrower(null);
            copyRepository.save(copies);
        }


        borrowerRepository.delete(borrower); // Delete the borrower
        model.addAttribute("borrowers", borrowerRepository.findAll());

        return "list-borrower";
    }

    //Section

    @GetMapping("listsection")
    public String showSectionUpdateForm(Model model) {
        model.addAttribute("sections", sectionRepository.findAll());
        return "list-section";
    }

    @GetMapping("signupsection")
    public String showSectionSignUpForm(Model model){
        model.addAttribute("section", new Section());
        return "add-section";
    }

    @PostMapping("addsection")
    public String addSection(@Valid Section section, BindingResult result) {
        if (result.hasErrors()) {
            return "add-section";
        }

        sectionRepository.save(section);
        return "redirect:listsection";
    }

    @GetMapping("updatesection")
    public String showSectionUpdateMainForm(Model model) {
        model.addAttribute("sections", sectionRepository.findAll());
        return "choose-section-to-update";
    }

    @GetMapping("editsection/{id}")
    public String showSectionUpdateForm(@PathVariable("id") long id, Model model) {
        Section section = sectionRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid section Id:" + id));
        model.addAttribute("section", section);
        return "update-section";
    }

    @PostMapping("updatesection/{id}")
    public String updateSection(@PathVariable("id") long id, @Valid Section section, BindingResult result, Model model) {
        if (result.hasErrors()) {
            section.setSectionId((int) id);
            return "index";
        }

        model.addAttribute("sections", sectionRepository.findAll());
        sectionRepository.save(section);
        return "list-section";
    }

    @GetMapping("deletesection")
    public String showSectionDeleteMainForm(Model model) {
        model.addAttribute("sections", sectionRepository.findAll());
        return "choose-section-to-delete";
    }

    @GetMapping("deletesection/{id}")
    public String deleteSection(@PathVariable("id") long id, Model model) {
        Section section = sectionRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid section Id:" + id));

        List<Book> book = section.getBooks();

        for (Book books : book) {
            books.setSection(null);
            bookRepository.save(books);
        }
        sectionRepository.delete(section);
        model.addAttribute("sections", sectionRepository.findAll());

        return "index";
    }

    //copy

    @GetMapping("listcopy")
    public String showCopyList(Model model) {
        model.addAttribute("copies", copyRepository.findAll());
        return "list-copy";
    }
    @GetMapping("signupcopy")
    public String showSignUpFormCopy(Copy copy, Model model){
        copy.setStatus("Available"); // Set the status here
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("borrowers", borrowerRepository.findAll());
        return "add-copy";
    }


    @PostMapping("addcopy")
    public String addCopy(@Valid Copy copy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-copy";
        }

        copyRepository.save(copy);
        return "redirect:listcopy";
    }

    @GetMapping("updatecopy")
    public String showUpdateMainFormCopy(Model model) {
        model.addAttribute("copies", copyRepository.findAll());
        return "choose-copy-to-update";
    }


    @GetMapping("editcopy/{id}")
    public String showUpdateFormCopy(@PathVariable("id") long id, Model model) {
        Copy copy = copyRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid copy Id:" + id));
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("borrowers", borrowerRepository.findAll());
        model.addAttribute("copy", copy);
        return "update-copy";
    }


    @PostMapping("updatecopy/{id}")
    public String updateCopy(@PathVariable("id") long id, @Valid Copy copy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            copy.setCopyId((int) id);
            return "update-copy";
        }

        model.addAttribute("copies", copyRepository.findAll());
        copyRepository.save(copy);
        return "list-copy";
    }

    @GetMapping("endloan/{id}")
    public String endLoan(@PathVariable("id") long id, Model model) {
        Copy copy = copyRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid copy Id:" + id));

        copy.setStatus("Available");
        copy.setStartDate(null);
        copy.setEndDate(null);
        copy.setBorrower(null);
        copyRepository.save(copy);
        model.addAttribute("copies", copyRepository.findAll());

        return "list-copy";
    }

    @GetMapping("deletecopy")
    public String showDeleteMainFormCopy(Model model) {
        model.addAttribute("copies", copyRepository.findAll());
        return "choose-copy-to-delete";
    }


    @GetMapping("deletecopy/{id}")
    public String deleteCopy(@PathVariable("id") long id, Model model) {
        Copy copy = copyRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid copy Id:" + id));


        copyRepository.delete(copy); // Delete the copy
        model.addAttribute("copies", copyRepository.findAll());

        return "list-copy";
    }

    //Employee
    @GetMapping("listemployee")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "list-employee";
    }

    @GetMapping("signupemployee")
    public String showSignUpFormEmployee(Model model) {
        model.addAttribute("employees", new Employee());
        return "add-employee";
    }


    @PostMapping("addemployee")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employee);
            return "add-employee";
        }
        employeeRepository.save(employee);
        return "redirect:listemployee";
    }

    @GetMapping("updateemployee")
    public String showUpdateMainFormEmployee(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "choose-employee-to-update";
    }


    @GetMapping("editemployee/{id}")
    public String showUpdateFormEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employees", employee);
        return "update-employee";
    }


    @PostMapping("updateemployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setEmployeeId((int) id);
            return "update-employee";
        }

        model.addAttribute("employees", employeeRepository.findAll());
        employeeRepository.save(employee);
        return "list-employee";
    }

    @GetMapping("deleteemployee")
    public String showDeleteMainFormEmployee(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "choose-employee-to-delete";
    }


    @GetMapping("deleteemployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));

        employeeRepository.delete(employee);
        model.addAttribute("employees", employeeRepository.findAll());

        return "list-employee";
    }
}