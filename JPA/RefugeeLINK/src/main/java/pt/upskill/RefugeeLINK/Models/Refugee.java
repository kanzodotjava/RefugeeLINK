package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Refugee extends Person {
    private String refugeeNumber;
    Formation formation;
    List<Formation> completedFormations;

}
