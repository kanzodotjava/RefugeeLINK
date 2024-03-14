package pt.upskill.RefugeeLINK.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;
import pt.upskill.RefugeeLINK.DTO.MentorRatingDTO;

import pt.upskill.RefugeeLINK.DTO.MentorUsernameDTO;
import pt.upskill.RefugeeLINK.Enums.Profession;
import pt.upskill.RefugeeLINK.Enums.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Mentor extends Person{
    Profession profession;
    String description;
    @OneToMany(mappedBy = "mentor")
    @JsonIgnoreProperties("mentor")
    @JsonManagedReference
    List<Refugee> refugees;

//    @Enumerated(EnumType.STRING)
    Status status = Status.AWAITING;

    double rating = 0.0;



    public Profession getProfession() {
        return profession;
    }

    public String getDescription() {
        return description;
    }

    public List<Refugee> getRefugee() {
        return refugees;
    }

    public Status getStatus() {
        return status;
    }

    public double getRating() {
        return rating;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setRefugee(Refugee refugee) {
        refugees.add(refugee);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public MentorDTO toDTO() {
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(this.getId());
        mentorDTO.setName(this.getName());
        mentorDTO.setUserName(this.getUserName());
        mentorDTO.setEmailAddress(this.getEmailAddress());
        mentorDTO.setBirthdayDate(this.getBirthdayDate());
        mentorDTO.setPhoneNumber(this.getPhoneNumber());
        mentorDTO.setCountry(this.getCountry());
        mentorDTO.setNationality(this.getNationality());
        mentorDTO.setPrimaryLanguage(this.getPrimaryLanguage());
        mentorDTO.setSecondaryLanguage(this.getSecondaryLanguage());
        mentorDTO.setCitizenCard(this.getCitizenCard());
        mentorDTO.setProfession(this.profession);
        mentorDTO.setStatus(this.status);
        mentorDTO.setDescription(this.description);
        mentorDTO.setRating(this.rating);
        return mentorDTO;
    }

    public MentorLoginDTO toLoginDTO() {
        MentorLoginDTO mentorLoginDTO = new MentorLoginDTO();
        mentorLoginDTO.setUserName(this.getUserName());
        mentorLoginDTO.setPassword(this.getPassword());
        return mentorLoginDTO;
    }

    public MentorUsernameDTO toUsernameDto() {
        MentorUsernameDTO mentorUsernameDto = new MentorUsernameDTO();
        mentorUsernameDto.setUsername(this.getUserName());
        return mentorUsernameDto;
    }

    public MentorRatingDTO toMentorRatingDTO(){
        MentorRatingDTO mentorRatingDTO = new MentorRatingDTO();
        mentorRatingDTO.setRating(this.rating);
        return mentorRatingDTO;
    }

}
