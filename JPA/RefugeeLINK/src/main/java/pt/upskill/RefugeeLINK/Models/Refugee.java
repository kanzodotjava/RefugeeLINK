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

    @OneToMany(mappedBy = "refugee")
    @Nullable
    private List<RefugeeFormation> completedFormations;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @Nullable
    private Mentor mentor;

    public String getRefugeeNumber() {
        return refugeeNumber;
    }

    @Nullable
    public Formation getFormation() {
        return formation;
    }

    @Nullable
    public List<RefugeeFormation> getCompletedFormations() {
        return completedFormations;
    }



    @Nullable
    public Mentor getMentor() {
        return mentor;
    }

    public void setRefugeeNumber(String refugeeNumber) {
        this.refugeeNumber = refugeeNumber;
    }

    public void setFormation(@Nullable Formation formation) {
        this.formation = formation;
    }

    public void setCompletedFormations(@Nullable List<RefugeeFormation> completedFormations) {
        this.completedFormations = completedFormations;
    }

    public void setMentor(@Nullable Mentor mentor) {
        this.mentor = mentor;
    }
}
