package ua.edu.nuos.lab5java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.lab5java.data.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}