package ua.edu.nuos.lab5java.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.lab5java.data.Company;
import ua.edu.nuos.lab5java.service.CompanyService;
import ua.edu.nuos.lab5java.service.UserService;

@Controller
@RequestMapping("/companies")
@AllArgsConstructor
class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;

    @GetMapping
    public String getCompanies(Model model) {
        model.addAttribute("companies", companyService.getCompanies());
        return "company/companies";
    }

    @GetMapping("/detail/{id}")
    public String getCompany(@PathVariable Integer id, Model model) {
        var company = companyService.getCompanyById(id);
        if (company == null) {
            throw new RuntimeException("Company with id " + id + " not found");
        }
        model.addAttribute("company", company);
        return "company/company_detail";
    }

    @PostMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompanyById(id);
        return "redirect:/companies";
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("users", userService.getUsers());
        return "company/create_company";
    }

    @GetMapping("/update/{id}")
    public String updateCompany(Model model, @PathVariable Integer id) {
        var company = companyService.getCompanyById(id);
        if (company == null) {
            throw new RuntimeException("Company " + id + " not found");
        }
        model.addAttribute("company", company);
        model.addAttribute("users", userService.getUsers());
        return "company/update_company";
    }

    @PostMapping("/save")
    public String createCompany(@ModelAttribute Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
    }

    @PostMapping("/update")
    public String updateCompany(@ModelAttribute Company company) {
        companyService.updateCompany(company);
        return "redirect:/companies";
    }
}
