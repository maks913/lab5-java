package ua.edu.nuos.lab5java.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.nuos.lab5java.service.CompanyService;
import ua.edu.nuos.lab5java.service.UserService;
import ua.edu.nuos.lab5java.service.VacancyService;

@Controller
@RequestMapping("/dashboard")
@AllArgsConstructor
class DashboardController {

    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final UserService userService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("vacancies", vacancyService.getVacancies().size());
        model.addAttribute("companies", companyService.getCompanies().size());
        model.addAttribute("users", userService.getUsers().size());
        model.addAttribute("averageSalary", vacancyService.getAverageSalary());
        model.addAttribute("topFiveCities", vacancyService.getVacanciesByCities());
        model.addAttribute("topFiveCompanies", vacancyService.getVacanciesByCompanies());
        model.addAttribute("maxSalary", vacancyService.getMaxSalary());
        model.addAttribute("minSalary", vacancyService.getMinSalary());
        return "dashboard/dashboard";
    }

}
