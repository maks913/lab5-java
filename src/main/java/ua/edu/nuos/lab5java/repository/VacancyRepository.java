package ua.edu.nuos.lab5java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.edu.nuos.lab5java.data.Vacancy;
import ua.edu.nuos.lab5java.projection.CityVacancyStats;
import ua.edu.nuos.lab5java.projection.CompanyVacancyStats;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    @Query("SELECT AVG(v.salary) from Vacancy v")
    BigDecimal getAverageSalary();

    @Query("SELECT v.city as city," +
            "   count(*) as countVacancies " +
            "from Vacancy v " +
            "group by v.city " +
            "order by count(*) desc")
    List<CityVacancyStats> getVacanciesByCity();

    @Query("""
        SELECT v.company.name as company,
            count(v) as countVacancies
        FROM Vacancy v
        GROUP BY v.company.id, v.company.name
        ORDER BY COUNT(v) DESC
    """)
    List<CompanyVacancyStats> getVacanciesByCompany();

    @Query("SELECT MAX(v.salary) FROM Vacancy v")
    BigDecimal getMaxSalary();

    @Query("SELECT MIN(v.salary) FROM Vacancy v")
    BigDecimal getMinSalary();
}