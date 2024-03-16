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

/**
 *  Represents a mentor with basic information such as name, contact details, and personal attributes.
 */
@Entity
public class Mentor extends Person{
    /**
     *  The profession of the mentor.
     */
    Profession profession;

    /**
     *  The description of the mentor.
     */
    String description;

    /**
     *  The list of refugees associated with the mentor.
     */
    @OneToMany(mappedBy = "mentor")
    @JsonIgnoreProperties("mentor")
    @JsonManagedReference
    List<Refugee> refugees;

    /**
     *  The status of the mentor.
     */
    Status status = Status.AWAITING;

    /**
     *  The rating of the mentor.
     */
    double rating = 0.0;

    /**
     *  Returns the profession of the mentor.
     * @return  The profession of the mentor.
     */
    public Profession getProfession() {
        return profession;
    }

    /**
     *  Returns the description of the mentor.
     * @return  The description of the mentor.
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Returns the list of refugees associated with the mentor.
     * @return  The list of refugees associated with the mentor.
     */
    public List<Refugee> getRefugee() {
        return refugees;
    }

    /**
     *  Returns the status of the mentor.
     * @return  The status of the mentor.
     */
    public Status getStatus() {
        return status;
    }

    /**
     *  Returns the rating of the mentor.
     * @return  The rating of the mentor.
     */
    public double getRating() {
        return rating;
    }

    /**
     *  Sets the profession of the mentor.
     * @param profession  The profession of the mentor.
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    /**
     *  Associates a refugee with the mentor.
     * @param refugee A refugee.
     */
    public void setRefugee(Refugee refugee) {
        refugees.add(refugee);
    }

    /**
     *  Sets the status of the mentor.
     * @param status  The status of the mentor.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *  Sets the description of the mentor.
     * @param description    The description of the mentor.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  Sets the rating of the mentor.
     * @param rating  The rating of the mentor.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     *  Converts the mentor to a DTO.
     * @return  The mentor as a DTO.
     */
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
        mentorDTO.setGender(this.getGender());
        return mentorDTO;
    }

    /**
     *  Converts the mentor to a login DTO.
     * @return  The mentor as a login DTO.
     */
    public MentorLoginDTO toLoginDTO() {
        MentorLoginDTO mentorLoginDTO = new MentorLoginDTO();
        mentorLoginDTO.setUserName(this.getUserName());
        mentorLoginDTO.setPassword(this.getPassword());
        return mentorLoginDTO;
    }

    /**
     *  Converts the mentor to a username DTO.
     * @return  The mentor as a username DTO.
     */
    public MentorUsernameDTO toUsernameDto() {
        MentorUsernameDTO mentorUsernameDto = new MentorUsernameDTO();
        mentorUsernameDto.setUsername(this.getUserName());
        return mentorUsernameDto;
    }

    /**
     *  Converts the mentor to a mentor rating DTO.
     * @return  The mentor as a mentor rating DTO.
     */
    public MentorRatingDTO toMentorRatingDTO(){
        MentorRatingDTO mentorRatingDTO = new MentorRatingDTO();
        mentorRatingDTO.setRating(this.rating);
        return mentorRatingDTO;
    }
}