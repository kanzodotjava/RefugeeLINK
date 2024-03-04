package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;
import pt.upskill.RefugeeLINK.Enums.Profession;
import pt.upskill.RefugeeLINK.Enums.Status;

@Entity
public class Mentor extends Person {
    private Profession profession;
    private Refugee refugee;
    private Status status;

}
