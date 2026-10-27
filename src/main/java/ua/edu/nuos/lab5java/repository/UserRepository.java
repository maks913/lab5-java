package ua.edu.nuos.lab5java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.lab5java.data.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = """
    INSERT INTO user_vacancies (user_id, vacancies_id)
    VALUES (:userId, :vacancyId)
    """, nativeQuery = true)
    public void addVacancy(
            @Param("userId") Integer userId,
            @Param("vacancyId") Integer vacancyId
    );

    @Transactional
    @Modifying
    @Query(value = "DELETE from user_vacancies where user_id = :userId and vacancies_id = :vacancyId",
            nativeQuery = true)
    void deleteSavedVacancy(@Param("userId") Integer userId,
                            @Param("vacancyId") Integer vacancyId);
}