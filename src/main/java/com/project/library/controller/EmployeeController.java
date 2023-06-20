package com.project.library.controller;

import com.project.library.model.Employee;
import com.project.library.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

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
