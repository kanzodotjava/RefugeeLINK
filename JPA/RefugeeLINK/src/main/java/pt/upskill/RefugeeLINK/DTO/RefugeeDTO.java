package pt.upskill.RefugeeLINK.DTO;

import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;
import pt.upskill.RefugeeLINK.Utils.Date;

public class RefugeeDTO {
    private Long id;
    private String name;
    private String emailAddress;
    private Date birthdayDate;
    private String phoneNumber;
    private Country country;
    private String userName;
    private String password;
    private Country nationality;
    private Language primaryLanguage;
    private Language secondaryLanguage;
    private String refugeeId;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Language getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(Language primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public Language getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(Language secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public String getRefugeeId() {
        return refugeeId;
    }

    public void setRefugeeId(String refugeeId) {
        this.refugeeId = refugeeId;
    }
}
