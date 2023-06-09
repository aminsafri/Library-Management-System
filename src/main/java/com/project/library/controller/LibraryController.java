package com.project.library.controller;

import com.project.library.model.Book;
import com.project.library.model.Borrower;
import com.project.library.repository.BookRepository;
import com.project.library.repository.BorrowerRepository;
import com.project.library.repository.SectionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class LibraryController {

    private final BookRepository bookRepository;

    private final SectionRepository sectionRepository;

    private final BorrowerRepository borrowerRepository;

    public LibraryController(BookRepository bookRepository, SectionRepository sectionRepository, BorrowerRepository borrowerRepository) {

        this.bookRepository = bookRepository;
        this.sectionRepository = sectionRepository;
        this.borrowerRepository = borrowerRepository;
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

        bookRepository.delete(book);
        model.addAttribute("books", bookRepository.findAll());

        return "list-book";
    }

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

}
