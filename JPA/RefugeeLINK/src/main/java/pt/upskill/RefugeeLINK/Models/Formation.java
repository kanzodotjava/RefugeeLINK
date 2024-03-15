package pt.upskill.RefugeeLINK.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import pt.upskill.RefugeeLINK.DTO.FormationDTO;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
/**
 * Represents a formation for a refugee.
 */
@Entity
public class Formation {
    /**
     * The unique identifier of the formation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the formation.
     */
    private String name;

    /**
     * The description of the formation.
     */
    private String description;

    /**
     * The number of lessons in the formation.
     */
    private int numberOfLessons;


    /**
     * The start date of the formation.
     */
    private LocalDate startDate;

    /**
     * The duration of the formation in weeks.
     */
    private int duration;


    /**
     * The status of the formation.
     */
    private FormationStatus status;

    /**
     * The ID of the organization that offers the formation.
     */
    private Long organizationId;

    /**
     * Retrieves the ID of the formation.
     * @return The ID of the formation.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retrieves the name of the formation.
     * @return The name of the formation.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the description of the formation.
     * @return The description of the formation.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the number of lessons in the formation.
     * @return The number of lessons.
     */
    public int getNumberOfLessons() {
        return numberOfLessons;
    }

    /**
     * Retrieves the start date of the formation.
     * @return The start date of the formation.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the status of the formation.
     * @return The status of the formation.
     */
    public FormationStatus getStatus() {
        return status;
    }

    /**
     * Retrieves the duration of the formation.
     * @return The duration of the formation.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Retrieves the organization ID associated with the formation.
     * @return The organization ID.
     */
    public Long getOrganizationId() {
        return organizationId;
    }

    /**
     * Sets the ID of the formation.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the formation.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the formation.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the number of lessons in the formation.
     * @param numberOfLessons The number of lessons to set.
     */
    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    /**
     * Sets the start date of the formation.
     * @param startDate The start date to set.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the status of the formation.
     * @param status The status to set.
     */
    public void setStatus(FormationStatus status) {
        this.status = status;
    }

    /**
     * Sets the duration of the formation.
     * @param duration The duration to set.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Sets the organization ID associated with the formation.
     * @param organizationId The organization ID to set.
     */
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * Converts the Formation object to a FormationDTO object.
     * @return The FormationDTO object representing the Formation.
     */
    public FormationDTO toDto(){
        FormationDTO formation = new FormationDTO();
        formation.setId(this.getId());
        formation.setName(this.getName());
        formation.setDescription(this.getDescription());
        formation.setNumberOfLessons(this.getNumberOfLessons());
        formation.setStartDate(this.getStartDate());
        formation.setDuration(this.getDuration());
        formation.setStatus(this.getStatus());
        formation.setOrganizationId(this.getOrganizationId());
        return formation;
    }
}
