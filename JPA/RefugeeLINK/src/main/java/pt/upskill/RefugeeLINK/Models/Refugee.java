package pt.upskill.RefugeeLINK.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Refugee extends Person {
    private String refugeeNumber;

    @ManyToOne
    @JoinColumn(name = "current_formation_id")
    private Formation formation;
    @ManyToMany
    @JoinTable(
            name = "refugee_completed_formations", // The join table name
            joinColumns = @JoinColumn(name = "refugee_id"), // The column for the refugee
            inverseJoinColumns = @JoinColumn(name = "formation_id") // The column for the formation
    )
    private List<Formation> completedFormations;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @Nullable
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
