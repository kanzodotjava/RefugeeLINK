package pt.upskill.RefugeeLINK.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;
import pt.upskill.RefugeeLINK.DTO.RefugeeDTO;
import pt.upskill.RefugeeLINK.DTO.RefugeeLoginDto;

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

    public RefugeeLoginDto toLoginDTO() {
        RefugeeLoginDto refugeeLoginDTO = new RefugeeLoginDto();
        refugeeLoginDTO.setUserName(this.getUserName());
        refugeeLoginDTO.setPassword(this.getPassword());
        return refugeeLoginDTO;
    }
}
