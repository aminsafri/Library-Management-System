package com.project.library.controller;

import com.project.library.model.Book;
import com.project.library.model.Borrower;
import com.project.library.model.Copy;
import com.project.library.model.Section;
import com.project.library.repository.BookRepository;
import com.project.library.repository.BorrowerRepository;
import com.project.library.repository.CopyRepository;
import com.project.library.repository.SectionRepository;
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

    public LibraryController(BookRepository bookRepository, SectionRepository sectionRepository, BorrowerRepository borrowerRepository, CopyRepository copyRepository) {

        this.bookRepository = bookRepository;
        this.sectionRepository = sectionRepository;
        this.borrowerRepository = borrowerRepository;
        this.copyRepository = copyRepository;
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

        List<Copy> copy = borrower.getCopy(); // Get the books associated with the borrower

        // Remove the borrower from the books
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
    public String showSectionSignUpForm(Section section){
        return "add-section";
    }

    @PostMapping("addsection")
    public String addSection(@Valid Section section, BindingResult result) {
        if (result.hasErrors()) {
            return "add-section";
        }

        sectionRepository.save(section);
        return "redirect:list";
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

        sectionRepository.delete(section);
        model.addAttribute("sections", sectionRepository.findAll());

        return "list-section";
    }

    //copy

    @GetMapping("listcopy")
    public String showCopyList(Model model) {
        model.addAttribute("copies", copyRepository.findAll());
        return "list-copy";
    }
    @GetMapping("signupcopy")
    public String showSignUpFormCopy(Copy copy, Model model){
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

//    @GetMapping("updateborrower")
//    public String showUpdateMainFormBorrower(Model model) {
//        model.addAttribute("borrowers", borrowerRepository.findAll());
//        return "choose-borrower-to-update";
//    }
//
//
//    @GetMapping("editborrower/{id}")
//    public String showUpdateFormBorrower(@PathVariable("id") long id, Model model) {
//        Borrower borrower = borrowerRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid borrower Id:" + id));
//        model.addAttribute("borrower", borrower);
//        return "update-borrower";
//    }
//
//
//    @PostMapping("updateborrower/{id}")
//    public String updateBorrower(@PathVariable("id") long id, @Valid Borrower borrower, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            borrower.setBorrowerId((int) id);
//            return "update-borrower";
//        }
//
//        model.addAttribute("borrowers", borrowerRepository.findAll());
//        borrowerRepository.save(borrower);
//        return "list-borrower";
//    }
//
//    @GetMapping("deleteborrower")
//    public String showDeleteMainFormBorrower(Model model) {
//        model.addAttribute("borrowers", borrowerRepository.findAll());
//        return "choose-borrower-to-delete";
//    }
//
//
//    @GetMapping("deleteborrower/{id}")
//    public String deleteBorrower(@PathVariable("id") long id, Model model) {
//        Borrower borrower = borrowerRepository.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid borrower Id:" + id));
//
//        List<Copy> copy = borrower.getCopy(); // Get the books associated with the borrower
//
//        // Remove the borrower from the books
//        for (Copy copies : copy) {
//            copies.setBorrower(null);
//            copyRepository.save(copies);
//        }
//
//        borrowerRepository.delete(borrower); // Delete the borrower
//
//        model.addAttribute("borrowers", borrowerRepository.findAll());
//        return "list-borrower";
//    }

}


