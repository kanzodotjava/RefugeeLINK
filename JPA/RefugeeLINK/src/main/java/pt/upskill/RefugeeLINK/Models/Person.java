package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;
import pt.upskill.RefugeeLINK.Utils.Date;



@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String emailAddress;
    private Date BirthdayDate;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Country country;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Country nationality;

    @Enumerated(EnumType.STRING)
    private Language primaryLanguage;

    @Enumerated(EnumType.STRING)
    private Language secondaryLanguage;
    private int citizenCard;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Date getBirthdayDate() {
        return BirthdayDate;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setBirthdayDate(Date birthdayDate) {
        BirthdayDate = birthdayDate;
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
}
