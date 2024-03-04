package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;
import pt.upskill.RefugeeLINK.Enums.Profession;
import pt.upskill.RefugeeLINK.Enums.Status;

@Entity
public class Mentor extends Person{
    Profession profession;
    Refugee refugee;
    Status status;

}
