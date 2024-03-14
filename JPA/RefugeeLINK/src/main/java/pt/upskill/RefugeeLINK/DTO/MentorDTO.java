package pt.upskill.RefugeeLINK.DTO;

import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;
import pt.upskill.RefugeeLINK.Enums.Profession;
import pt.upskill.RefugeeLINK.Enums.Status;

import java.time.LocalDate;
import java.util.List;

public class MentorDTO {
    private Long id;
    private String name;
    private String emailAddress;
    private LocalDate birthdayDate;
    private String phoneNumber;
    private Country country;
    private String userName;
    private String password;
    private Country nationality;
    private Language primaryLanguage;
    private Language secondaryLanguage;
    private int citizenCard;
    private Profession profession;
    private List<Long> refugeeIds;

    private String description;
    private Status status;
    private double rating;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Country getCountry() {
        return country;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Country getNationality() {
        return nationality;
    }

    public Language getPrimaryLanguage() {
        return primaryLanguage;
    }

    public Language getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public int getCitizenCard() {
        return citizenCard;
    }

    public String getDescription() {
        return description;
    }

    public Profession getProfession() {
        return profession;
    }

    public List<Long> getRefugeeIds() {
        return refugeeIds;
    }

    public Status getStatus() {
        return status;
    }

    public double getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setPrimaryLanguage(Language primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public void setSecondaryLanguage(Language secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public void setCitizenCard(int citizenCard) {
        this.citizenCard = citizenCard;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setRefugeeIds(List<Long> refugeeIds) {
        this.refugeeIds = refugeeIds;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
