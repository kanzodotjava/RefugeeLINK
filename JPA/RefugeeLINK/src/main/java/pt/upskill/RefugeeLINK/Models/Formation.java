package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;

import java.util.Date;

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

}
