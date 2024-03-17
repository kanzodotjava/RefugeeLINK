package pt.upskill.RefugeeLINK.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;
import pt.upskill.RefugeeLINK.DTO.RefugeeDTO;
import pt.upskill.RefugeeLINK.DTO.RefugeeLoginDto;
import pt.upskill.RefugeeLINK.DTO.RefugeeMsgDTO;

import java.util.List;

/**
 * Represents a refugee with basic information such as name, contact details, and personal attributes.
 */
@Entity
public class Refugee extends Person {

    /**
     * The unique identifier of the refugee.
     */
    private String refugeeNumber;

    /**
     * The current formation of the refugee.
     */
    @ManyToOne
    @JoinColumn(name = "current_formation_id")
    @Nullable
    private Formation formation;

    /**
     *  The list of formations completed by the refugee.
     */
    @OneToMany(mappedBy = "refugee")
    @Nullable
    @JsonIgnoreProperties("refugee")
    private List<RefugeeFormation> completedFormations;

    /**
     *  The mentor of the refugee.
     */
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @Nullable
    @JsonIgnoreProperties("refugees") // to avoid infinite recursion
    @JsonBackReference
    private Mentor mentor;

    /**
     * Retrieves the number of the refugee.
     *
     * @return The number of the refugee.
     */
    public String getRefugeeNumber() {
        return refugeeNumber;
    }

    /**
     *  Retrieves the current formation of the refugee.
     *
     * @return  The current formation of the refugee.
     */
    @Nullable
    public Formation getFormation() {
        return formation;
    }

    /**
     *  Retrieves the list of formations completed by the refugee.
     *
     * @return  The list of formations completed by the refugee.
     */
    @Nullable
    public List<RefugeeFormation> getCompletedFormations() {
        return completedFormations;
    }

    /**
     *  Retrieves the mentor of the refugee.
     *
     * @return  The mentor of the refugee.
     */
    @Nullable
    public Mentor getMentor() {
        return mentor;
    }

    /**
     * Sets the number of the refugee.
     *
     * @param refugeeNumber The number of the refugee.
     */
    public void setRefugeeNumber(String refugeeNumber) {
        this.refugeeNumber = refugeeNumber;
    }

    /**
     *  Sets the current formation of the refugee.
     *
     * @param formation The current formation of the refugee.
     */
    public void setFormation(@Nullable Formation formation) {
        this.formation = formation;
    }

    /**
     *  Sets the list of formations completed by the refugee.
     *
     * @param completedFormations   The list of formations completed by the refugee.
     */
    public void setCompletedFormations(@Nullable List<RefugeeFormation> completedFormations) {
        this.completedFormations = completedFormations;
    }

    /**
     *  Sets the mentor of the refugee.
     *
     * @param mentor    The mentor of the refugee.
     */
    public void setMentor(@Nullable Mentor mentor) {
        this.mentor = mentor;
    }

    /**
     *  Converts the object to a DTO.
     *
     * @return  The DTO representation of the object.
     */
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
        refugeeDTO.setGender(this.getGender());
        return refugeeDTO;
    }

    /**
     *  Converts the object to a login DTO.
     *
     * @return  The DTO representation of the object.
     */
    public RefugeeLoginDto toLoginDTO() {
        RefugeeLoginDto refugeeLoginDTO = new RefugeeLoginDto();
        refugeeLoginDTO.setUserName(this.getUserName());
        refugeeLoginDTO.setPassword(this.getPassword());
        return refugeeLoginDTO;
    }

    /**
     *  Converts the object to a message DTO.
     *
     * @return  The DTO message representation of the object.
     */
    public RefugeeMsgDTO toMsgDTO() {
        RefugeeMsgDTO refugeeMsgDTO = new RefugeeMsgDTO();
        refugeeMsgDTO.setUsername(this.getUserName());
        return refugeeMsgDTO;
    }
}
