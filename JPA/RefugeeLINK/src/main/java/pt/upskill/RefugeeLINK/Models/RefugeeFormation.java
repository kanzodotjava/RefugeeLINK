package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;

@Entity
public class RefugeeFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refugee_id")
    private Refugee refugee;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    private boolean isApproved;

    public Long getId() {
        return id;
    }

    public Refugee getRefugee() {
        return refugee;
    }

    public Formation getFormation() {
        return formation;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRefugee(Refugee refugee) {
        this.refugee = refugee;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}

