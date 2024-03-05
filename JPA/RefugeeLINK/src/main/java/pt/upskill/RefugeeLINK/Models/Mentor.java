package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.Enums.Profession;
import pt.upskill.RefugeeLINK.Enums.Status;

import java.util.List;

@Entity
public class Mentor extends Person{
    Profession profession;

    String description;
    @OneToMany(mappedBy = "mentor")
    List<Refugee> refugee;
    Status status;

    public Profession getProfession() {
        return profession;
    }

    public String getDescription() {
        return description;
    }

    public List<Refugee> getRefugee() {
        return refugee;
    }

    public Status getStatus() {
        return status;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setRefugee(List<Refugee> refugee) {
        this.refugee = refugee;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MentorDTO toDTO() {
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(this.getId());
        mentorDTO.setName(this.getName());
        mentorDTO.setEmailAddress(this.getEmailAddress());
        mentorDTO.setBirthdayDate(this.getBirthdayDate());
        mentorDTO.setPhoneNumber(this.getPhoneNumber());
        mentorDTO.setCountry(this.getCountry());
        mentorDTO.setUserName(this.getUserName());
        mentorDTO.setPassword(this.getPassword());
        mentorDTO.setNationality(this.getNationality());
        mentorDTO.setPrimaryLanguage(this.getPrimaryLanguage());
        mentorDTO.setSecondaryLanguage(this.getSecondaryLanguage());
        mentorDTO.setCitizenCard(this.getCitizenCard());
        mentorDTO.setProfession(this.profession);
        mentorDTO.setStatus(this.status);
        return mentorDTO;
    }

}
