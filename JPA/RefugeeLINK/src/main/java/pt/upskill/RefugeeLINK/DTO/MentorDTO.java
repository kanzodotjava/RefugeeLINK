package pt.upskill.RefugeeLINK.DTO;

import pt.upskill.RefugeeLINK.Enums.*;

import java.time.LocalDate;
import java.util.List;

/**
 *  Mentor DTO
 */

public class MentorDTO {
    /**
     *  Mentor id
     */
    private Long id;

    /**
     *  Mentor name
     */
    private String name;

    /**
     *  Mentor email
     */
    private String emailAddress;

    /**
     *  Mentor birthday
     */
    private LocalDate birthdayDate;

    /**
     *  Mentor phone number
     */
    private String phoneNumber;

    /**
     *  Mentor country
     */
    private Country country;

    /**
     *  Mentor username
     */
    private String userName;

    /**
     *  Mentor password
     */
    private String password;

    /**
     *  Mentor nationality
     */
    private Country nationality;

    /**
     *  Mentor primary language
     */
    private Language primaryLanguage;

    /**
     *  Mentor secondary language
     */
    private Language secondaryLanguage;

    /**
     *  Mentor citizen card
     */
    private int citizenCard;

    /**
     *  Mentor profession
     */
    private Profession profession;

    /**
     *  Mentor list of refugee ids
     */
    private List<Long> refugeeIds;

    /**
     *  Mentor description
     */
    private String description;

    /**
     *  Mentor status
     */
    private Status status;

    /**
     *  Mentor rating
     */
    private double rating;

    /**
     *  Mentor gender
     */
    private Gender gender;

    /**
     *  Return mentor id
     * @return  Mentor id
     */
    public Long getId() {
        return id;
    }

    /**
     *  Return mentor name
     * @return  Mentor name
     */
    public String getName() {
        return name;
    }

    /**
     *  Return mentor email
     * @return  Mentor email
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *  Return mentor birthday
     * @return  Mentor birthday
     */
    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    /**
     *  Return mentor phone number
     * @return  Mentor phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *  Return mentor country
     * @return  Mentor country
     */
    public Country getCountry() {
        return country;
    }

    /**
     *  Return mentor gender
     * @return  Mentor gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     *  Return mentor username
     * @return  Mentor username
     */
    public String getUserName() {
        return userName;
    }

    /**
     *  Return mentor password
     * @return  Mentor password
     */
    public String getPassword() {
        return password;
    }

    /**
     *  Return mentor nationality
     * @return  Mentor nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     *  Return mentor primary language
     * @return  Mentor primary language
     */
    public Language getPrimaryLanguage() {
        return primaryLanguage;
    }

    /**
     *  Return mentor secondary language
     * @return  Mentor secondary language
     */
    public Language getSecondaryLanguage() {
        return secondaryLanguage;
    }

    /**
     *  Return mentor citizen card
     * @return  Mentor citizen card
     */
    public int getCitizenCard() {
        return citizenCard;
    }

    /**
     *  Return mentor description
     * @return  Mentor description
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Return mentor profession
     * @return  Mentor profession
     */
    public Profession getProfession() {
        return profession;
    }

    /**
     *  Return mentor list of refugee ids
     * @return  Mentor list of refugee ids
     */
    public List<Long> getRefugeeIds() {
        return refugeeIds;
    }

    /**
     *  Return mentor status
     * @return  Mentor status
     */
    public Status getStatus() {
        return status;
    }

    /**
     *  Return mentor rating
     * @return  Mentor rating
     */
    public double getRating() {
        return rating;
    }

    /**
     *  Set mentor id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  Set mentor name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Set mentor email
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *  Set mentor birthday
     * @param birthdayDate
     */
    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    /**
     *  Set mentor phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *  Set mentor country
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     *  Set mentor gender
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     *  Set mentor username
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *  Set mentor password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *  Set mentor nationality
     * @param nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     *  Set mentor primary language
     * @param primaryLanguage
     */
    public void setPrimaryLanguage(Language primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    /**
     *  Set mentor secondary language
     * @param secondaryLanguage
     */
    public void setSecondaryLanguage(Language secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    /**
     *  Set mentor citizen card
     * @param citizenCard
     */
    public void setCitizenCard(int citizenCard) {
        this.citizenCard = citizenCard;
    }

    /**
     *  Set mentor profession
     * @param profession
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    /**
     *  Set mentor list of refugee ids
     * @param refugeeIds
     */
    public void setRefugeeIds(List<Long> refugeeIds) {
        this.refugeeIds = refugeeIds;
    }

    /**
     *  Set mentor description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  Set mentor status
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *  Set mentor rating
     * @param rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
}
