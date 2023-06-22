package com.project.library.controller;

import com.project.library.model.Book;
import com.project.library.model.Section;
import com.project.library.repository.BookRepository;
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
@RequestMapping("/sections")
public class SectionController {

    private final SectionRepository sectionRepository;
    private final BookRepository bookRepository;

    public SectionController(SectionRepository sectionRepository, BookRepository bookRepository) {
        this.sectionRepository = sectionRepository;
        this.bookRepository = bookRepository;
    }

    // All the section-related methods you have in the original controller go here
    // ...
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
}
