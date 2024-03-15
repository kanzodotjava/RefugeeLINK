package pt.upskill.RefugeeLINK.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import pt.upskill.RefugeeLINK.DTO.RefugeeFormationDTO;

@Entity
public class RefugeeFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refugee_id")
    @JsonIgnoreProperties("mentor") // to avoid infinite recursion
    private Refugee refugee;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties("refugees")
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


    public RefugeeFormationDTO toDto() {
        RefugeeFormationDTO dto = new RefugeeFormationDTO();
        dto.setId(this.id);
        dto.setRefugeeId(this.refugee.getId());
        dto.setRefugeeName(this.refugee.getName());
        dto.setFormationId(this.formation.getId());
        dto.setFormationName(this.formation.getName());
        dto.setApproved(this.isApproved);
        return dto;
    }
}

