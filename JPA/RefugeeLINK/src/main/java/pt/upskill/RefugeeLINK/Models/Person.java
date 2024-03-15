package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;
import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Gender;
import pt.upskill.RefugeeLINK.Enums.Language;

import java.time.LocalDate;

/**
 * Represents a person with basic information such as name, contact details, and personal attributes.
 */

@MappedSuperclass
public abstract class Person {
    /**
     * The unique identifier of the person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The email address of the person.
     */
    private String emailAddress;

    /**
     * The date of birth of the person.
     */
    private LocalDate BirthdayDate;

    /**
     * The phone number of the person.
     */
    private String phoneNumber;

    /**
     * The gender of the person.
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * The country of residence of the person.
     */
    @Enumerated(EnumType.STRING)
    private Country country;

    /**
     * The username of the person for login purposes.
     */
    private String userName;

    /**
     * The password of the person for login purposes.
     */
    private String password;

    /**
     * The nationality of the person.
     */
    @Enumerated(EnumType.STRING)
    private Country nationality;

    /**
     * The primary language spoken by the person.
     */
    @Enumerated(EnumType.STRING)
    private Language primaryLanguage;

    /**
     * The secondary language spoken by the person.
     */
    @Enumerated(EnumType.STRING)
    private Language secondaryLanguage;

    /**
     * The citizen card number of the person.
     */
    private int citizenCard;

    /**
     * Retrieves the ID of the person.
     * @return The ID of the person.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retrieves the name of the person.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the email address of the person.
     * @return The email address of the person.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Retrieves the date of birth of the person.
     * @return The date of birth of the person.
     */
    public LocalDate getBirthdayDate() {
        return BirthdayDate;
    }

    /**
     * Retrieves the phone number of the person.
     * @return The phone number of the person.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Retrieves the country of residence of the person.
     * @return The country of residence of the person.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Retrieves the username of the person.
     * @return The username of the person.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieves the password of the person.
     * @return The password of the person.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the nationality of the person.
     * @return The nationality of the person.
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Retrieves the primary language spoken by the person.
     * @return The primary language spoken by the person.
     */
    public Language getPrimaryLanguage() {
        return primaryLanguage;
    }

    /**
     * Retrieves the secondary language spoken by the person.
     * @return The secondary language spoken by the person.
     */
    public Language getSecondaryLanguage() {
        return secondaryLanguage;
    }

    /**
     * Retrieves the citizen card number of the person.
     * @return The citizen card number of the person.
     */
    public int getCitizenCard() {
        return citizenCard;
    }

    /**
     * Retrieves the gender of the person.
     * @return The gender of the person.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the ID of the person.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the person.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email address of the person.
     * @param emailAddress The email address to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Sets the date of birth of the person.
     * @param birthdayDate The date of birth to set.
     */
    public void setBirthdayDate(LocalDate birthdayDate) {
        BirthdayDate = birthdayDate;
    }

    /**
     * Sets the phone number of the person.
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the country for this object.
     *
     * @param  country   the country to be set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Sets the username of the person.
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the password of the person.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the nationality of the person.
     *
     * @param nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Sets the primary language spoken by the person.
     *
     * @param primaryLanguage
     */
    public void setPrimaryLanguage(Language primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    /**
     * Sets the secondary language spoken by the person.
     *
     * @param secondaryLanguage
     */
    public void setSecondaryLanguage(Language secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    /**
     * Sets the citizen card number of the person.
     *
     * @param citizenCard
     */
    public void setCitizenCard(int citizenCard) {
        this.citizenCard = citizenCard;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }


}
