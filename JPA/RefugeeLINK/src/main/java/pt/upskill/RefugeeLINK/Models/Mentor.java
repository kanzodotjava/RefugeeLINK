package pt.upskill.RefugeeLINK.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import pt.upskill.RefugeeLINK.Enums.Profession;
import pt.upskill.RefugeeLINK.Enums.Status;

import java.util.List;

@Entity
public class Mentor extends Person{
    Profession profession;

    @OneToMany(mappedBy = "mentor")
    List<Refugee> refugee;
    Status status;

    public Profession getProfession() {
        return profession;
    }

    public List<Refugee> getRefugee() {
        return refugee;
    }

    public Status getStatus() {
        return status;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setRefugee(List<Refugee> refugee) {
        this.refugee = refugee;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
