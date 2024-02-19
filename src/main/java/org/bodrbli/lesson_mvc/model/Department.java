package org.bodrbli.lesson_mvc.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments_t")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parentDpt;
    @OneToMany(mappedBy = "parentDpt")
    private Set<Department> subDepartments;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", parentDpt=" + (parentDpt == null? "null" : String.valueOf(parentDpt.getId())) +
                '}';
    }
}
