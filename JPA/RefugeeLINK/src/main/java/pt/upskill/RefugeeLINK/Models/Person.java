package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;

import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String emailAddress;
    private Date BirthdayDate;
    private String phoneNumber;
    private Country country;
    private String userName;
    private String password;
    private Country nationality;
    private Language primaryLanguage;
    private Language secondaryLanguage;
    private int citizenCard;



}
