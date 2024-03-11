package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;

import java.util.Date;
import java.util.List;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    int numberOfLessons;
    Date startDate;
    int duration;

    FormationStatus status;
    @OneToMany(mappedBy = "formation")
    private List<RefugeeFormation> refugees;
    @ManyToOne
    private Organization organization;

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

    public Date getStartDate() {
        return startDate;
    }

    public FormationStatus getStatus() {
        return status;
    }

    public int getDuration() {
        return duration;
    }

    public Organization getOrganization() {
        return organization;
    }

    public List<RefugeeFormation> getRefugees() {
        return refugees;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStatus(FormationStatus status) {
        this.status = status;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRefugees(List<RefugeeFormation> refugees) {
        this.refugees = refugees;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


}
