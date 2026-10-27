package ua.edu.nuos.lab5java.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.lab5java.data.Company;
import ua.edu.nuos.lab5java.data.User;
import ua.edu.nuos.lab5java.data.Vacancy;
import ua.edu.nuos.lab5java.repository.CompanyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public void deleteCompanyById(Integer id) {
        Company company = companyRepository.findById(id).orElse(null);

        if (company != null) {
            for (Vacancy vacancy : company.getVacancies()) {
                for (User user : vacancy.getUsers()) {
                    user.getVacancies().remove(vacancy);
                }
                vacancy.getUsers().clear();
            }
        }

        companyRepository.deleteById(id);
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public void updateCompany(Company formCompany) {
        Company existingCompany = companyRepository.findById(formCompany.getId())
                .orElse(null);

        if (existingCompany != null) {
            existingCompany.setName(formCompany.getName());
            existingCompany.setQuantityEmployees(formCompany.getQuantityEmployees());
            existingCompany.setCreatedAt(formCompany.getCreatedAt());
            existingCompany.setDescription(formCompany.getDescription());
            existingCompany.setUser(formCompany.getUser());
        }
    }
}
