package pt.upskill.RefugeeLINK.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Refugee extends Person {
    private String refugeeNumber;

    @ManyToOne
    @JoinColumn(name = "current_formation_id")
    @Nullable
    private Formation formation;

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

    public Mentor getMentor() {
        return mentor;
    }

    public void setRefugeeNumber(String refugeeNumber) {
        this.refugeeNumber = refugeeNumber;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
}
