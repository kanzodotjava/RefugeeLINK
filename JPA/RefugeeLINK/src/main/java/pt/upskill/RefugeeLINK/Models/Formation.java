package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    int numberOfLessons;
    Date startDate;
    Date endDate;
    @ManyToMany
    @JoinTable(
            name = "formation_refugee", // The join table name
            joinColumns = @JoinColumn(name = "formation_id"), // The column for the formation
            inverseJoinColumns = @JoinColumn(name = "refugee_id") // The column for the refugee
    )
    List<Refugee> students;
//    Map<Refugee,Boolean> isApproved;
}
