package pt.upskill.RefugeeLINK.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pt.upskill.RefugeeLINK.DTO.RefugeeDTO;

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


    public RefugeeDTO toDTO() {
        RefugeeDTO refugeeDTO = new RefugeeDTO();
        refugeeDTO.setId(this.getId());
        refugeeDTO.setName(this.getName());
        refugeeDTO.setEmailAddress(this.getEmailAddress());
        refugeeDTO.setBirthdayDate(this.getBirthdayDate());
        refugeeDTO.setPhoneNumber(this.getPhoneNumber());
        refugeeDTO.setCountry(this.getCountry());
        refugeeDTO.setNationality(this.getNationality());
        refugeeDTO.setPrimaryLanguage(this.getPrimaryLanguage());
        refugeeDTO.setSecondaryLanguage(this.getSecondaryLanguage());
        refugeeDTO.setRefugeeId(this.getRefugeeNumber());

        return refugeeDTO;
    }
}
