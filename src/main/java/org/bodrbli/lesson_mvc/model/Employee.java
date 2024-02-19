package org.bodrbli.lesson_mvc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees_t")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    Profession profession;
    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;
}
