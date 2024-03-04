package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    List<Refugee> students;
    Map<Refugee,Boolean> isApproved;
}
