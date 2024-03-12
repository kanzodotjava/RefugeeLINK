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

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int numberOfLessons;
    private LocalDate startDate;
    private int duration;
    private FormationStatus status;
    private Long organizationId;

//    @ManyToOne
//    private Organization organization;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfLessons() {
        return numberOfLessons;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public FormationStatus getStatus() {
        return status;
    }

    public int getDuration() {
        return duration;
    }



    public Long getOrganizationId() {
        return organizationId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStatus(FormationStatus status) {
        this.status = status;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

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
