package ua.edu.nuos.lab5java.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.lab5java.data.Vacancy;
import ua.edu.nuos.lab5java.service.CompanyService;
import ua.edu.nuos.lab5java.service.VacancyService;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@AllArgsConstructor
class VacancyController {

    private final VacancyService vacancyService;
    private final CompanyService companyService;

    @GetMapping
    public String getVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getVacancies());
        return "vacancy/vacancies";
    }

    @GetMapping("/new")
    public String getNewVacancy(Model model) {
        model.addAttribute("vacancy", new Vacancy());
        model.addAttribute("companies", companyService.getCompanies());
        return "vacancy/create_vacancy";
    }

    @GetMapping("/update/{id}")
    public String getUpdateVacancy(Model model, @PathVariable Integer id) {
        var vacancy = vacancyService.getVacancyById(id);
        if (vacancy == null) {
            throw new RuntimeException("Vacancy " + id + " not found");
        }
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("companies", companyService.getCompanies());
        return "vacancy/update_vacancy";
    }

    @GetMapping("/detail/{id}")
    public String getDetailVacancy(Model model, @PathVariable Integer id) {
        var vacancy = vacancyService.getVacancyById(id);
        if (vacancy == null) {
            throw new RuntimeException("Vacancy " + id + " not found");
        }
        model.addAttribute("vacancy", vacancy);
        return "vacancy/vacancy_detail";
    }

    @PostMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable Integer id) {
        if (id == null) throw new RuntimeException("Vacancy not found");
        vacancyService.deleteVacancyById(id);
        return "redirect:/vacancies";
    }

    @PostMapping("/save")
    public String createVacancy(@ModelAttribute Vacancy vacancy) {
        vacancyService.saveVacancy(vacancy);
        return "redirect:/vacancies";
    }

}
