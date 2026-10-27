package ua.edu.nuos.lab5java.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "quantity_employees")
    private Integer quantityEmployees;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}