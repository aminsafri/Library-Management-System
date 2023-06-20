package com.project.library.controller;

import com.project.library.model.Borrower;
import com.project.library.model.Copy;
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

import java.util.List;

@Controller
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerRepository borrowerRepository;
    private final CopyRepository copyRepository;

    public BorrowerController(BorrowerRepository borrowerRepository, CopyRepository copyRepository) {
        this.borrowerRepository = borrowerRepository;
        this.copyRepository = copyRepository;
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

}
