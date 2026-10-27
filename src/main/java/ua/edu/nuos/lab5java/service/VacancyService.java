package ua.edu.nuos.lab5java.service;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.lab5java.data.Vacancy;
import ua.edu.nuos.lab5java.projection.CityVacancyStats;
import ua.edu.nuos.lab5java.projection.CompanyVacancyStats;
import ua.edu.nuos.lab5java.repository.VacancyRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy getVacancyById(Integer id) {
        return vacancyRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteVacancyById(Integer id) {
        if (vacancyRepository.existsById(id)) vacancyRepository.deleteById(id);
    }

    @Transactional
    public void saveVacancy(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    public BigDecimal getAverageSalary() {
        return vacancyRepository.getAverageSalary().setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public List<CityVacancyStats> getVacanciesByCities() {
        var vacancies = vacancyRepository.getVacanciesByCity();
        if (vacancies.size() < 5) return vacancies;
        return vacancies.subList(0, 5);
    }

    public List<CompanyVacancyStats> getVacanciesByCompanies() {
        var vacancies = vacancyRepository.getVacanciesByCompany();
        if (vacancies.size() < 5) return vacancies;
        return vacancies.subList(0, 5);
    }

    public BigDecimal getMaxSalary() {
        return vacancyRepository.getMaxSalary();
    }

    public BigDecimal getMinSalary() {
        return vacancyRepository.getMinSalary();
    }
}
