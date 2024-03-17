package pt.upskill.RefugeeLINK.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import pt.upskill.RefugeeLINK.DTO.RefugeeFormationDTO;

/**
 *  Represents a relationship between a refugee and a formation.
 */
@Entity
public class RefugeeFormation {
    /**
     *  The ID of the relationship.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  The refugee that is part of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "refugee_id")
    @JsonIgnoreProperties("mentor") // to avoid infinite recursion
    private Refugee refugee;

    /**
     *  The formation that is part of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties("refugees")
    private Formation formation;

    /**
     *  Whether the refugee has been approved or not.
     */
    private boolean isApproved;

    /**
     *  Retrieves the ID of the relationship.
     * @return  The ID of the relationship.
     */
    public Long getId() {
        return id;
    }

    /**
     *  Retrieves the refugee that is part of the relationship.
     * @return  The refugee that is part of the relationship.
     */
    public Refugee getRefugee() {
        return refugee;
    }

    /**
     *  Retrieves the formation that is part of the relationship.
     * @return  The formation that is part of the relationship.
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     *  Whether the refugee has been approved or not.
     * @return  Whether the refugee has been approved or not.
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     *  Sets the ID of the relationship.
     * @param id     The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  Sets the refugee that is part of the relationship.
     * @param refugee    The refugee to set.
     */
    public void setRefugee(Refugee refugee) {
        this.refugee = refugee;
    }

    /**
     *  Sets the formation that is part of the relationship.
     * @param formation   The formation to set.
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     *  Sets whether the refugee has been approved or not.
     * @param approved    Whether the refugee has been approved or not.
     */
    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    /**
     *  Converts the relationship to a DTO.
     * @return  The DTO representation of the relationship.
     */
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

