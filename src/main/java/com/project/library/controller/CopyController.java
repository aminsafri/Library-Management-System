package com.project.library.controller;

import com.project.library.model.Copy;
import com.project.library.repository.BookRepository;
import com.project.library.repository.BorrowerRepository;
import com.project.library.repository.CopyRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/copies")
public class CopyController {

    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public CopyController(CopyRepository copyRepository, BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.copyRepository = copyRepository;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

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
}
