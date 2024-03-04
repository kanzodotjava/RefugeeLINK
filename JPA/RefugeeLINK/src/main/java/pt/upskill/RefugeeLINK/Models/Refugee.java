package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Refugee extends Person {
    private String refugeeNumber;

    @ManyToOne
    Formation formation;
    List<Formation> completedFormations;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public String getRefugeeNumber() {
        return refugeeNumber;
    }

    public Formation getFormation() {
        return formation;
    }

    public List<Formation> getCompletedFormations() {
        return completedFormations;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setRefugeeNumber(String refugeeNumber) {
        this.refugeeNumber = refugeeNumber;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void setCompletedFormations(List<Formation> completedFormations) {
        this.completedFormations = completedFormations;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
}
